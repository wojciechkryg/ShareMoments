package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.model.Filter

class EditPhotoPresenter(override val view: EditPhotoContract.View) : EditPhotoContract.Presenter {

    override fun editImageWithFilter(filter: Filter) {
        view.applyImageFilter(filter)
    }

    override fun onAttach() {
        view.loadTemporaryPhoto()
    }

    override fun onDetach() {}
}
