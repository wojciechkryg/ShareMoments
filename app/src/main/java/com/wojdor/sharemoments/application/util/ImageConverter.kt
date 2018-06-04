package com.wojdor.sharemoments.application.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageConverter {

    companion object {
        const val PNG_QUALITY = 100

        private const val START_X = 0
        private const val START_Y = 0
        private const val DEFAULT_SIZE = 1
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = if (drawable.intrinsicWidth <= START_X || drawable.intrinsicHeight <= START_Y) {
            Bitmap.createBitmap(DEFAULT_SIZE, DEFAULT_SIZE, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(START_X, START_Y, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun drawableToBase64String(drawable: Drawable): String {
        val bitmap = drawableToBitmap(drawable)
        return bitmapToBase64String(bitmap)
    }

    fun drawableToByteArray(drawable: Drawable): ByteArray {
        val bitmap = drawableToBitmap(drawable)
        return bitmapToByteArray(bitmap)
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, PNG_QUALITY, outputStream)
        return outputStream.toByteArray()
    }

    fun bitmapToBase64String(bitmap: Bitmap): String {
        val bytes = bitmapToByteArray(bitmap)
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}
