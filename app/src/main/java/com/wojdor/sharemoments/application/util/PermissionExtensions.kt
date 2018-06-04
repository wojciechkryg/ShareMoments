package com.wojdor.sharemoments.application.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

const val REQUEST_LOCATION_PERMISSION_CODE = 200
const val REQUEST_WRITE_PERMISSION_CODE = 201

fun Activity.askForLocationPermission() {
    if (isPermissionRequired(Manifest.permission.ACCESS_FINE_LOCATION)) {
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION_PERMISSION_CODE)
    }
}

fun Activity.askForWriteStoragePermission() {
    if (isPermissionRequired(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_PERMISSION_CODE)
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

fun Activity.checkPermissionGranted(permission: String, permissions: Array<out String>,
                                    grantResults: IntArray, grantedCallback: () -> Unit) {
    if (permissions.contains(permission)) {
        val permissionIndex = permissions.indexOf(permission)
        checkPermission(grantResults[permissionIndex], grantedCallback)
    }
}

private fun checkPermission(grantResult: Int, grantedCallback: () -> Unit) {
    if (grantResult == PackageManager.PERMISSION_GRANTED) {
        grantedCallback()
    }
}
