package com.wojdor.sharemoments.application.gallery

import android.content.Intent
import android.os.Bundle
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.takephoto.TakePhotoActivity
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : BaseActivity(), GalleryContract.View {

    override val presenter: GalleryContract.Presenter = GalleryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setupViews()
    }

    private fun setupViews() {
        galleryAddFab.setOnClickListener { presenter.showAddPhoto() }
    }

    override fun openAddPhoto() {
        startActivity(Intent(this, TakePhotoActivity::class.java))
    }
}
