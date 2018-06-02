package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.data.service.PhotoService
import com.wojdor.sharemoments.domain.Miniature
import com.wojdor.sharemoments.domain.mapper.MiniatureMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GalleryPresenter(override val view: GalleryContract.View) : GalleryContract.Presenter {

    companion object {
        private const val PHOTOS_PER_PAGE = 10
    }

    private val disposables by lazy { CompositeDisposable() }

    override fun onAttach() {
        // TODO: load proper page
        downloadPhotos(0)
    }

    private fun downloadPhotos(page: Int, quantity: Int = PHOTOS_PER_PAGE) {
        disposables.add(PhotoService.instance.getMiniatures(quantity, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val mapper = MiniatureMapper()
                    view.showMiniatures(it.data.map { mapper.map(it) })
                }, {
                    // TODO: show error
                }))
    }

    override fun showPhotoDetails(miniature: Miniature) {
        view.openPhotoDetails(miniature.id)
    }

    override fun onDetach() {
        disposables.clear()
    }

    override fun showAddPhoto() {
        view.openAddPhoto()
    }
}
