package com.wojdor.sharemoments.application.view

import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.IOException

class CameraPreview(context: Context, private val camera: Camera) : SurfaceView(context), SurfaceHolder.Callback, AnkoLogger {

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (error: IOException) {
            debug { error.message }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
        if (holder.surface == null) {
            return
        }
        try {
            camera.stopPreview()
        } catch (error: Exception) {
            debug { error.message }
        }
        surfaceCreated(holder)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}
}