package com.wojdor.sharemoments.application.util

import android.Manifest
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.LocationServices

class LocationProvider(private val activity: Activity) {

    fun getLastLocation(callback: (Location?) -> Unit) {
        if (activity.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation.addOnSuccessListener { callback.invoke(it) }
        }
    }
}
