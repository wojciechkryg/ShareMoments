package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.domain.Miniature

interface GalleryContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun showMiniatures(miniatures: List<Miniature>)

        fun openAddPhoto()

        fun openPhotoDetails(id: Int)
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun showAddPhoto()

        fun showPhotoDetails(miniature: Miniature)
    }
}
