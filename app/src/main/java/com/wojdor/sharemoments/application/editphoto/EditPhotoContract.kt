package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.base.BaseContract
import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.data.model.PhotoUploadModel

interface EditPhotoContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun loadTemporaryPhoto()

        fun applyImageFilter(filter: Filter)

        fun saveBitmap()
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun editImageWithFilter(filter: Filter)

        fun deleteFilters()

        fun sendImage(photoUploadModel: PhotoUploadModel, onSuccess: () -> Unit, onError: () -> Unit)

        fun saveImage()
    }
}
