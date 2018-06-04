package com.wojdor.sharemoments.application.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class FileStorage(private val context: Context) {

    companion object {
        private const val PICTURE_EXTENSION = ".png"
    }

    fun store(filename: String, photo: ByteArray) {
        try {
            context.openFileOutput(filename, Context.MODE_PRIVATE).run {
                write(photo)
                close()
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    fun obtain(filename: String): ByteArray {
        var bytes = byteArrayOf()
        try {
            context.openFileInput(filename).run {
                bytes = readBytes()
                close()
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
        return bytes
    }

    fun saveAsPicture(bitmap: Bitmap, filename: String) {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(path, "$filename$PICTURE_EXTENSION")
        if (file.exists()) {
            file.delete()
        }
        FileOutputStream(file).run {
            bitmap.compress(Bitmap.CompressFormat.PNG, ImageConverter.PNG_QUALITY, this)
            flush()
            close()
        }
    }
}
