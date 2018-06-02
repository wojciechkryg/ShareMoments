package com.wojdor.sharemoments.application.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.LocationServices

class LocationProvider(private val activity: Activity) {

    @SuppressLint("MissingPermission")
    fun getLastLocation(callback: (Location?) -> Unit) {
        // TODO: get gps location
        if (activity.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation.addOnSuccessListener { callback(it) }
        }
    }
}
