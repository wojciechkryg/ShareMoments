package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.data.service.PhotoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GalleryPresenter(override val view: GalleryContract.View) : GalleryContract.Presenter {

    private val disposables by lazy { CompositeDisposable() }

    companion object {
        private const val PHOTOS_PER_PAGE = 10
    }

    override fun onAttach() {
        // TODO: load proper page
        downloadPhotos(1)
    }

    private fun downloadPhotos(page: Int, quantity: Int = PHOTOS_PER_PAGE) {
        disposables.add(PhotoService.instance.getMiniatures(quantity, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // TODO: show results
                }, {
                    // TODO: show error
                }))
    }

    override fun onDetach() {
        disposables.clear()
    }

    override fun showAddPhoto() {
        view.openAddPhoto()
    }
}
