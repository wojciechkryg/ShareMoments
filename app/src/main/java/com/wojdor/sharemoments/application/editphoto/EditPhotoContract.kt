package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.domain.PhotoUpload

interface EditPhotoContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun loadTemporaryPhoto()

        fun applyImageFilter(filter: Filter)

        fun saveBitmap()

        fun openGallery()

        fun showLoading()

        fun dismissLoading()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun editImageWithFilter(filter: Filter)

        fun deleteFilters()

        fun sendImage(photoUploadModel: PhotoUpload)

        fun saveImage()
    }
}
