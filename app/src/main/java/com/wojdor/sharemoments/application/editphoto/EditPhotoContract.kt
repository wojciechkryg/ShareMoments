package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.base.BasePresenter
import com.wojdor.sharemoments.application.base.BaseView

interface EditPhotoContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter<View>
}
