package com.wojdor.sharemoments.application.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.util.askForPermission
import com.wojdor.sharemoments.application.util.isPermissionGranted
import com.wojdor.sharemoments.application.view.CameraPreview
import kotlinx.android.synthetic.main.activity_take_photo.*

class TakePhotoActivity : BaseActivity(), TakePhotoContract.View {

    override val presenter = TakePhotoPresenter(this)
    private val cameraPreview: CameraPreview by lazy { CameraPreview(this, camera) }
    private val camera: Camera by lazy {
        initCamera()
    }

    companion object {

        private const val FIRST_INDEX = 0
        private const val CAMERA_REQUEST_CODE = 1
        private const val PORTRAIT_ORIENTATION = 90
    }

    private fun initCamera(): Camera {
        val camera = Camera.open()
        camera.setDisplayOrientation(PORTRAIT_ORIENTATION)
        return camera
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        presenter.requestCameraPermission()
        initViews()
    }

    private fun initViews() {
        takePhotoTakePhotoFab.setOnClickListener {
            // TODO: take photo
        }
    }

    override fun checkCameraPermission() {
        if (isPermissionGranted(Manifest.permission.CAMERA)) {
            takePhotoPreviewFl.addView(cameraPreview)
        } else {
            askForPermission(Manifest.permission.CAMERA, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> if (permissionDenied(grantResults)) onBackPressed()
        }
    }

    private fun permissionDenied(grantResults: IntArray) =
            (grantResults.isNotEmpty() && grantResults[TakePhotoActivity.FIRST_INDEX]
                    == PackageManager.PERMISSION_DENIED)
}
