package com.wojdor.sharemoments.application.gallery

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.DisplayMetrics
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.photodetails.PhotoDetailsActivity
import com.wojdor.sharemoments.application.photodetails.PhotoDetailsActivity.Companion.PHOTO_ID_EXTRA
import com.wojdor.sharemoments.application.takephoto.TakePhotoActivity
import com.wojdor.sharemoments.application.util.addPagination
import com.wojdor.sharemoments.domain.Miniature
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : BaseActivity(), GalleryContract.View {

    companion object {
        private const val MIN_NUMBER_OF_COLUMNS = 2
        private const val COLUMN_WIDTH_DIVIDER = 500
    }

    override val presenter: GalleryContract.Presenter = GalleryPresenter(this)
    override var isLoading: Boolean = false

    private val adapter by lazy { GalleryAdapter { presenter.showPhotoDetails(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setupViews()
        setupMiniaturesRv()
        presenter.onAttach()
    }

    private fun setupViews() {
        galleryAddFab.setOnClickListener { presenter.showAddPhoto() }
    }

    private fun setupMiniaturesRv() {
        galleryMiniaturesRv.adapter = adapter
        val layoutManager = GridLayoutManager(this, calculateNumberOfColumns())
        galleryMiniaturesRv.layoutManager = layoutManager
        galleryMiniaturesRv.addPagination(layoutManager,
                { shouldLoadMore() }, { presenter.downloadNextPageOfMiniatures() })
    }

    private fun shouldLoadMore() = !isLoading && !presenter.isLastPage

    private fun calculateNumberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val numberOfColumns = displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER
        return if (numberOfColumns < MIN_NUMBER_OF_COLUMNS) {
            MIN_NUMBER_OF_COLUMNS
        } else {
            numberOfColumns
        }
    }

    override fun addMiniatures(miniatures: List<Miniature>) {
        adapter.addMiniatures(miniatures)
    }

    override fun openAddPhoto() {
        startActivity(Intent(this, TakePhotoActivity::class.java))
    }

    override fun openPhotoDetails(id: Int) {
        val intent = Intent(this, PhotoDetailsActivity::class.java).apply {
            putExtra(PHOTO_ID_EXTRA, id)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
