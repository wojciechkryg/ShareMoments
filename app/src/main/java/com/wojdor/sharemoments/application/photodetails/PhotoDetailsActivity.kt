package com.wojdor.sharemoments.application.photodetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.domain.Photo
import kotlinx.android.synthetic.main.activity_photo_details.*

class PhotoDetailsActivity : BaseActivity(), PhotoDetailsContract.View {

    companion object {
        const val PHOTO_ID_EXTRA = "com.wojdor.sharemoments.application.photodetails.PHOTO_ID_EXTRA"
    }

    override val presenter = PhotoDetailsPresenter(this)

    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        presenter.onAttach()
        loadPhoto()
    }

    private fun loadPhoto() {
        val photoId = intent.extras.getInt(PHOTO_ID_EXTRA)
        presenter.downloadPhoto(photoId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.photo_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_location -> {
                // TODO: show location
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showPhoto(photo: Photo) {
        Glide.with(this)
                .load(photo.photoUrl)
                .transition(withCrossFade())
                .into(photoDetailsPhotoIv)
    }

    override fun showLocationMenuItem() {
        menu.findItem(R.id.action_location).isVisible = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
