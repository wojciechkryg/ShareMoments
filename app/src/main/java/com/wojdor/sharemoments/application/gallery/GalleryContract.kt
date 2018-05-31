package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.domain.Miniature
import com.wojdor.sharemoments.domain.Photo

interface GalleryContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun showMiniatures(miniatures: List<Miniature>)

        fun openAddPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun showAddPhoto()
    }
}
