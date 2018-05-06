package com.wojdor.sharemoments.application.takephoto

import com.wojdor.sharemoments.application.base.BasePresenter
import com.wojdor.sharemoments.application.base.BaseView

interface TakePhotoContract {

    interface View : BaseView<Presenter> {

        fun capturePhoto()

        fun storeTemporaryPhoto(photo: ByteArray)

        fun openEditPhoto()
    }

    interface Presenter : BasePresenter<View> {

        fun takePhoto()

        fun showEditPhoto(photo: ByteArray)
    }
}
