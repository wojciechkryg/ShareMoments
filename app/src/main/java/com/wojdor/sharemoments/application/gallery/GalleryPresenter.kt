package com.wojdor.sharemoments.application.gallery

import com.wojdor.sharemoments.data.service.PhotoService
import com.wojdor.sharemoments.domain.MetaData
import com.wojdor.sharemoments.domain.Miniature
import com.wojdor.sharemoments.domain.mapper.MetaDataMapper
import com.wojdor.sharemoments.domain.mapper.MiniatureMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GalleryPresenter(override val view: GalleryContract.View) : GalleryContract.Presenter {

    companion object {
        const val PAGE_SIZE = 10
    }

    override val isLastPage
        get() = (PAGE_SIZE * (metadata.page + 1)) >= metadata.totalCount
                && (PAGE_SIZE * metadata.page) <= metadata.totalCount

    private val disposables by lazy { CompositeDisposable() }
    private val metadataMapper by lazy { MetaDataMapper() }
    private val miniatureMapper by lazy { MiniatureMapper() }
    private var page = 0
    private lateinit var metadata: MetaData

    override fun onAttach() {
        downloadNextPageOfMiniatures()
    }

    override fun downloadNextPageOfMiniatures() {
        view.showLoading()
        disposables.add(PhotoService.instance.getMiniatures(PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    page++
                    metadata = metadataMapper.map(it.metaData)
                    view.addMiniatures(it.data.map { miniatureMapper.map(it) })
                    view.dismissLoading()
                }, {
                    view.dismissLoading()
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
