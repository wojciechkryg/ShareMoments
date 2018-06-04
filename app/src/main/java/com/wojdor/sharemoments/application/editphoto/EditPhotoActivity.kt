package com.wojdor.sharemoments.application.editphoto

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.gallery.GalleryActivity
import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.application.util.FileStorage
import com.wojdor.sharemoments.application.util.FilterProvider
import com.wojdor.sharemoments.application.util.ImageConverter
import com.wojdor.sharemoments.application.util.ImageLoader
import com.wojdor.sharemoments.domain.PhotoUpload
import com.wojdor.sharemoments.domain.mapper.PhotoUploadMapper
import kotlinx.android.synthetic.main.activity_edit_photo.*
import java.util.*

class EditPhotoActivity : BaseActivity(), EditPhotoContract.View {

    companion object {
        const val TEMPORARY_PHOTO_EXTRA = "com.wojdor.sharemoments.application.editphoto.TEMP_PHOTO_EXTRA"
        const val LONGITUDE_EXTRA = "com.wojdor.sharemoments.application.editphoto.LONGITUDE_EXTRA"
        const val LATITUDE_EXTRA = "com.wojdor.sharemoments.application.editphoto.LATITUDE_EXTRA"

        private const val PHOTO_EXTENSION = "jpeg"
        private const val PHOTO_MIMETYPE = "image/jpeg"
    }

    override val presenter = EditPhotoPresenter(this)

    private val imageLoader by lazy { ImageLoader(editPhotoPhotoIv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo)
        setupViews()
        presenter.onAttach()
    }

    private fun setupViews() {
        setupFiltersRv()
        setupDeleteFiltersFab()
        setupAcceptFab()
        setupSaveFab()
    }

    private fun setupFiltersRv() {
        with(editPhotoFiltersRv) {
            adapter = FilterAdapter(FilterProvider().filters) { presenter.editImageWithFilter(it) }
            layoutManager = LinearLayoutManager(this@EditPhotoActivity,
                    LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupDeleteFiltersFab() {
        editPhotoDeleteFiltersFab.setOnClickListener { presenter.deleteFilters() }
    }

    private fun setupAcceptFab() {
        editPhotoAcceptFab.setOnClickListener {
            val photoUpload = createPhotoUpload()
            val photoUploadModel = PhotoUploadMapper().map(photoUpload)
            presenter.sendImage(photoUploadModel, {
                openGalleryActivity()
            }, {
                // TODO: onError
            })
        }
    }

    private fun setupSaveFab() {
        editPhotoSaveFab.setOnClickListener {
            // TODO: save image
        }
    }

    private fun createPhotoUpload(): PhotoUpload {
        val image = ImageConverter().drawableToBase64String(editPhotoPhotoIv.drawable)
        val date = Calendar.getInstance().time.toString()
        val longitude = intent.extras[LONGITUDE_EXTRA] as? Double?
        val latitude = intent.extras[LATITUDE_EXTRA] as? Double?
        return PhotoUpload(date, longitude, latitude, image, PHOTO_EXTENSION, PHOTO_MIMETYPE)
    }

    private fun openGalleryActivity() {
        val intent = Intent(this, GalleryActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    override fun loadTemporaryPhoto() {
        val filename = intent.extras.getString(TEMPORARY_PHOTO_EXTRA)
        val bytes = FileStorage(this).obtain(filename)
        imageLoader.loadImage(bytes)
    }

    override fun applyImageFilter(filter: Filter) {
        filter.applyFilter(editPhotoPhotoIv)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
