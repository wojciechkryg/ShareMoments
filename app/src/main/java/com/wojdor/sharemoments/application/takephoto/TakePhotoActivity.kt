package com.wojdor.sharemoments.application.takephoto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.Gesture
import com.otaliastudios.cameraview.GestureAction
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity.Companion.FILENAME_EXTRA
import kotlinx.android.synthetic.main.activity_take_photo.*

class TakePhotoActivity : BaseActivity(), TakePhotoContract.View {

    companion object {
        const val TEMP_PHOTO_FILENAME = "tempFile"
    }

    override val presenter = TakePhotoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        setupViews()
    }

    private fun setupViews() {
        setupCameraView()
        setupTakePhotoFab()
    }

    private fun setupCameraView() {
        setupCameraListener()
        setupCameraGestures()
    }

    private fun setupCameraListener() {
        takePhotoCameraView.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(photo: ByteArray?) {
                photo?.let { presenter.showEditPhoto(it) }
            }
        })
    }

    private fun setupCameraGestures() {
        takePhotoCameraView.mapGesture(Gesture.PINCH, GestureAction.ZOOM)
        takePhotoCameraView.mapGesture(Gesture.TAP, GestureAction.FOCUS_WITH_MARKER)
    }

    private fun setupTakePhotoFab() {
        takePhotoTakePhotoFab.setOnClickListener { presenter.takePhoto() }
    }

    override fun onResume() {
        super.onResume()
        takePhotoCameraView.start()
    }

    override fun onPause() {
        super.onPause()
        takePhotoCameraView.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        takePhotoCameraView.destroy()
    }

    override fun capturePhoto() {
        takePhotoCameraView.capturePicture()
    }

    override fun storeTemporaryPhoto(photo: ByteArray) {
        try {
            openFileOutput(TEMP_PHOTO_FILENAME, Context.MODE_PRIVATE).run {
                write(photo)
                close()
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    override fun openEditPhoto() {
        val intent = Intent(this, EditPhotoActivity::class.java)
        intent.putExtra(FILENAME_EXTRA, TEMP_PHOTO_FILENAME)
        startActivity(intent)
    }
}
