package com.wojdor.sharemoments.application.util

import android.content.Context

class FileStorage(private val context: Context) {

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
}
