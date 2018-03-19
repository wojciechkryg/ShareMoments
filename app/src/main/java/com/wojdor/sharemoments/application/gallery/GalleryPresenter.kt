package com.wojdor.sharemoments.application.gallery

class GalleryPresenter(override val view: GalleryContract.View) : GalleryContract.Presenter {

    override fun showAddPhoto() {
        view.openAddPhoto()
    }
}
