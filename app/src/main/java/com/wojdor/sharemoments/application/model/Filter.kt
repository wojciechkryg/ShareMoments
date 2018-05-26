package com.wojdor.sharemoments.application.model

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.wojdor.sharemoments.application.util.ImageViewConverter

data class Filter(val resIcon: Int, private val transformation: Transformation<Bitmap>) {

    fun applyFilter(imageView: ImageView) {
        ImageViewConverter(imageView).convertImage(transformation)
    }
}
