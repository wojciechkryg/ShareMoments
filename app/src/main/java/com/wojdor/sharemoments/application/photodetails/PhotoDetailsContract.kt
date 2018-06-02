package com.wojdor.sharemoments.application.photodetails

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.domain.Photo

interface PhotoDetailsContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun showPhoto(photo: Photo)

        fun showLocationMenuItem()

        fun openMapDialog(longitude: Double, latitude: Double)
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun downloadPhoto(id: Int)

        fun showMapDialog()
    }
}
