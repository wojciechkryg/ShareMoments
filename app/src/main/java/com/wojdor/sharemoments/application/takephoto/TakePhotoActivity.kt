package com.wojdor.sharemoments.application.takephoto

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.Gesture
import com.otaliastudios.cameraview.GestureAction
import com.wojdor.sharemoments.R
import com.wojdor.sharemoments.application.base.BaseActivity
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity.Companion.LATITUDE_EXTRA
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity.Companion.LONGITUDE_EXTRA
import com.wojdor.sharemoments.application.editphoto.EditPhotoActivity.Companion.TEMPORARY_PHOTO_EXTRA
import com.wojdor.sharemoments.application.util.*
import kotlinx.android.synthetic.main.activity_take_photo.*

class TakePhotoActivity : BaseActivity(), TakePhotoContract.View {

    companion object {
        const val TEMP_PHOTO_FILENAME = "photo.tmp"
    }

    override val presenter = TakePhotoPresenter(this)

    private val locationProvider by lazy { LocationProvider(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        setupViews()
        presenter.onAttach()
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
        askForLocationPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION_CODE -> handleLocationPermission(permissions, grantResults)
        }
    }

    private fun handleLocationPermission(permissions: Array<out String>, grantResults: IntArray) {
        checkPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION, permissions, grantResults) {
            enableLocation()
        }
    }

    override fun onPause() {
        super.onPause()
        takePhotoCameraView.stop()
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        takePhotoCameraView.destroy()
    }

    override fun enableLocation() {
        saveLastKnownLocation()
        locationProvider.addLocationListener {
            presenter.longitude = it?.longitude
            presenter.latitude = it?.latitude
        }
    }

    private fun saveLastKnownLocation() {
        presenter.longitude = locationProvider.lastKnownLocation?.longitude
        presenter.latitude = locationProvider.lastKnownLocation?.latitude
    }

    override fun disableLocationListener() {
        locationProvider.removeLocationListener()
    }

    override fun capturePhoto() {
        takePhotoCameraView.capturePicture()
    }

    override fun storeTemporaryPhoto(photo: ByteArray) {
        FileStorage(this).store(TEMP_PHOTO_FILENAME, photo)
    }

    override fun openEditPhoto() {
        val intent = Intent(this, EditPhotoActivity::class.java).apply {
            putExtra(TEMPORARY_PHOTO_EXTRA, TEMP_PHOTO_FILENAME)
            putExtra(LONGITUDE_EXTRA, presenter.longitude)
            putExtra(LATITUDE_EXTRA, presenter.latitude)
        }
        startActivity(intent)
    }
}
