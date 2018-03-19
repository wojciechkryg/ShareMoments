package com.wojdor.sharemoments.application.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

fun Context.isPermissionGranted(permission: String) =
        (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED)

fun Activity.askForPermission(permission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(this,
            arrayOf(permission), requestCode)
}
