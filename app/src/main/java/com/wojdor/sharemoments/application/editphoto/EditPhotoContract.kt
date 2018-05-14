package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.base.BaseContract

interface EditPhotoContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun loadTemporaryPhoto()
    }

    interface Presenter : BaseContract.BasePresenter<View>
}
