package com.wojdor.sharemoments.application.takephoto

class TakePhotoPresenter(override val view: TakePhotoContract.View) : TakePhotoContract.Presenter {

    override var longitude: Double? = null
    override var latitude: Double? = null

    override fun onAttach() {}

    override fun onDetach() {}

    override fun takePhoto() {
        view.capturePhoto()
    }

    override fun showEditPhoto(photo: ByteArray) {
        view.storeTemporaryPhoto(photo)
        view.openEditPhoto()
    }
}
