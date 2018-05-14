package com.wojdor.sharemoments.application.editphoto

class EditPhotoPresenter(override val view: EditPhotoContract.View) : EditPhotoContract.Presenter {

    override fun onAttach() {
        view.loadTemporaryPhoto()
    }

    override fun onDetach() {}
}
