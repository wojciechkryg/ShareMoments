package com.wojdor.sharemoments.application.photodetails

import com.wojdor.sharemoments.data.service.PhotoService
import com.wojdor.sharemoments.domain.mapper.PhotoMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotoDetailsPresenter(override val view: PhotoDetailsContract.View) : PhotoDetailsContract.Presenter {

    private val disposables by lazy { CompositeDisposable() }

    override fun onAttach() {}

    override fun downloadPhoto(id: Int) {
        disposables.add(PhotoService.instance.getPhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showPhoto(PhotoMapper().map(it))
                }, {
                    // TODO: show error
                })
        )
    }

    override fun onDetach() {
        disposables.clear()
    }
}
