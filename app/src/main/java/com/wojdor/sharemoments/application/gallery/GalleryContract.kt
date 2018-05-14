package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BaseContract

interface GalleryContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun openAddPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun showAddPhoto()
    }
}
