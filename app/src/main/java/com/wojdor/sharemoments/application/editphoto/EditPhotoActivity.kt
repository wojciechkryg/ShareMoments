package com.wojdor.sharemoments.application.editphoto

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.application.util.FilterProvider
import kotlinx.android.synthetic.main.activity_edit_photo.*

class EditPhotoActivity : BaseActivity(), EditPhotoContract.View {

    companion object {
        const val TEMPORARY_PHOTO_EXTRA = "com.wojdor.sharemoments.application.editphoto.TEMP_PHOTO_EXTRA"
    }

    override val presenter = EditPhotoPresenter(this)

    private val filterProvider by lazy { FilterProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo)
        setupFiltersRv()
        presenter.onAttach()
    }

    private fun setupFiltersRv() {
        with(editPhotoFiltersRv) {
            adapter = FiltersAdapter(filterProvider.filters) { presenter.editImageWithFilter(it) }
            layoutManager = LinearLayoutManager(this@EditPhotoActivity,
                    LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun loadTemporaryPhoto() {
        val filename = intent.extras.getString(TEMPORARY_PHOTO_EXTRA)
        try {
            openFileInput(filename).run {
                Glide.with(this@EditPhotoActivity)
                        .asBitmap()
                        .load(readBytes())
                        .transition(withCrossFade())
                        .into(editPhotoPhotoIv)
                close()
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    override fun applyImageFilter(filter: Filter) {
        filter.applyFilter(editPhotoPhotoIv)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
