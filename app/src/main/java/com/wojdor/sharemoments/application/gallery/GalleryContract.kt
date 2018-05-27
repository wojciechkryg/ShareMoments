package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.domain.Photo

interface GalleryContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun addPhotos(photos: List<Photo>)

        fun openAddPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun showAddPhoto()
    }
}
