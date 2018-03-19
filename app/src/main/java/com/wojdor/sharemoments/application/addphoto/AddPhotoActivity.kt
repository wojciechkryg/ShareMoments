package com.wojdor.sharemoments.application.gallery

import android.os.Bundle
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity

class AddPhotoActivity : BaseActivity(), AddPhotoContract.View {

    override val presenter = AddPhotoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)
    }
}
