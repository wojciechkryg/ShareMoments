package com.wojdor.sharemoments.application.takephoto

import com.wojdor.sharemoments.application.base.BaseContract

interface TakePhotoContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun capturePhoto()

        fun storeTemporaryPhoto(photo: ByteArray)

        fun openEditPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun takePhoto()

        fun showEditPhoto(photo: ByteArray)
    }
}
