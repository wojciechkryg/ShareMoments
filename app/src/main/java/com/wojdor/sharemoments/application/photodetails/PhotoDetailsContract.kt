package com.wojdor.sharemoments.application.photodetails

import com.wojdor.sharemoments.application.base.BaseContract

interface PhotoDetailsContract {

    interface View : BaseContract.BaseView<Presenter> {

    }

    interface Presenter : BaseContract.BasePresenter<View> {

    }
}
