package com.wojdor.sharemoments.application.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImageViewConverter(private val imageView: ImageView) {

    companion object {
        const val START_X = 0
        const val START_Y = 0
    }

    private val context = imageView.context
    private var lastBitmap = convertDrawableToBitmap(imageView.drawable)

    fun convertImage(transformation: Transformation<Bitmap>) {
        val bitmap = convertDrawableToBitmap(imageView.drawable)
        Glide.with(context)
                .setDefaultRequestOptions(RequestOptions().placeholder(BitmapDrawable(context.resources, bitmap)))
                .load(bitmap)
                .apply(RequestOptions.bitmapTransform(transformation))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        lastBitmap.recycle()
        lastBitmap = bitmap
    }

    private fun convertDrawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(START_X, START_Y, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
