package com.wojdor.sharemoments.application.takephoto

import com.wojdor.sharemoments.application.base.BaseContract

interface TakePhotoContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun enableLocationListener()

        fun disableLocationListener()

        fun capturePhoto()

        fun storeTemporaryPhoto(photo: ByteArray)

        fun openEditPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        var longitude: Double?

        var latitude: Double?

        fun takePhoto()

        fun showEditPhoto(photo: ByteArray)
    }
}
