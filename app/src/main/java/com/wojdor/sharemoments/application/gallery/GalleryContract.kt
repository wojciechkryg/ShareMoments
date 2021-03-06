package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.domain.Miniature

interface GalleryContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun addMiniatures(miniatures: List<Miniature>)

        fun openAddPhoto()

        fun openPhotoDetails(id: Int)

        fun showLoading()

        fun dismissLoading()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        val isLastPage: Boolean

        fun downloadNextPageOfMiniatures()

        fun showAddPhoto()

        fun showPhotoDetails(miniature: Miniature)
    }
}
