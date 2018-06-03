package com.wojdor.sharemoments.application.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

const val REQUEST_PERMISSION_CODE = 200

fun Activity.askForLocationPermission() {
    if (isPermissionRequired(Manifest.permission.ACCESS_FINE_LOCATION)) {
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_PERMISSION_CODE)
    }
}

fun Context.isPermissionGranted(permission: String) =
        (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED)

fun Context.isPermissionRequired(permission: String) =
        (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED)

fun Activity.askForPermission(permission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(this,
            arrayOf(permission), requestCode)
}
