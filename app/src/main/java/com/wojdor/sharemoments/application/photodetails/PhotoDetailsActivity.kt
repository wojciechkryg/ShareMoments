package com.wojdor.sharemoments.application.photodetails

import android.os.Bundle
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity

class PhotoDetailsActivity : BaseActivity(), PhotoDetailsContract.View {

    companion object {
        const val PHOTO_ID_EXTRA = "com.wojdor.sharemoments.application.photodetails.PHOTO_ID_EXTRA"
    }

    override val presenter = PhotoDetailsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        presenter.onAttach()
    }
}
