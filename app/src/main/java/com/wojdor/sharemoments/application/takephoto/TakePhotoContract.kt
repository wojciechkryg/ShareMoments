package com.wojdor.sharemoments.application.takephoto

import com.wojdor.sharemoments.application.base.BasePresenter
import com.wojdor.sharemoments.application.base.BaseView

interface TakePhotoContract {

    interface View : BaseView<Presenter> {

        fun capturePhoto()

        fun openEditPhoto(photo: ByteArray)
    }

    interface Presenter : BasePresenter<View> {

        fun takePhoto()

        fun showEditPhoto(photo: ByteArray)
    }
}
