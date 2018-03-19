package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BasePresenter
import com.wojdor.sharemoments.application.base.BaseView

interface TakePhotoContract {

    interface View : BaseView<Presenter> {

        fun checkCameraPermission()
    }

    interface Presenter : BasePresenter<View> {

        fun requestCameraPermission()
    }
}
