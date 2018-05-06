package com.wojdor.sharemoments.application.splash

import android.content.Intent
import android.os.Bundle
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.gallery.GalleryActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, GalleryActivity::class.java))
        finish()
    }
}
