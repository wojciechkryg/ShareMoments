package com.wojdor.sharemoments.application.editphoto

import com.wojdor.sharemoments.application.model.Filter
import com.wojdor.sharemoments.data.service.PhotoService
import com.wojdor.sharemoments.domain.PhotoUpload
import com.wojdor.sharemoments.domain.mapper.PhotoUploadMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EditPhotoPresenter(override val view: EditPhotoContract.View) : EditPhotoContract.Presenter {

    private val disposables by lazy { CompositeDisposable() }

    override fun editImageWithFilter(filter: Filter) {
        view.applyImageFilter(filter)
    }

    override fun deleteFilters() {
        view.loadTemporaryPhoto()
    }

    override fun sendImage(photoUpload: PhotoUpload) {
        val photoUploadModel = PhotoUploadMapper().map(photoUpload)
        disposables.add(PhotoService.instance.uploadPhoto(photoUploadModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.openGallery()
                    view.dismissLoading()
                }, {
                    view.dismissLoading()
                }))
    }

    override fun saveImage() {
        view.saveBitmap()
    }

    override fun onAttach() {
        view.loadTemporaryPhoto()
    }

    override fun onDetach() {
        disposables.clear()
    }
}
