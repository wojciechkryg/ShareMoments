package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.application.base.BasePresenter
import com.wojdor.sharemoments.application.base.BaseView

interface GalleryContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}
