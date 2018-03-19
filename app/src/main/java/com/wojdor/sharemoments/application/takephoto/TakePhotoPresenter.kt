package com.wojdor.sharemoments.application.gallery

class TakePhotoPresenter(override val view: TakePhotoContract.View) : TakePhotoContract.Presenter {

    override fun requestCameraPermission() {
        view.checkCameraPermission()
    }
}
