package com.wojdor.sharemoments.application.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageConverter {

    companion object {
        private const val PNG_QUALITY = 100
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(ImageViewConverter.START_X, ImageViewConverter.START_Y, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun drawableToBase64String(drawable: Drawable): String {
        val bitmap = drawableToBitmap(drawable)
        return bitmapToBase64String(bitmap)
    }

    fun bitmapToBase64String(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, PNG_QUALITY, outputStream)
        val bytes = outputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}
