package com.wojdor.sharemoments.application.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import java.util.concurrent.TimeUnit

class LocationProvider(private val activity: Activity) {

    companion object {
        private val MINIMUM_TIME_INTERVAL = TimeUnit.MINUTES.toMillis(1)
        private const val MINIMUM_DISTANCE = 100F
    }

    private val locationManager by lazy { activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    private lateinit var locationListener: LocationListener

    @SuppressLint("MissingPermission")
    fun addLocationListener(callback: (Location?) -> Unit) {
        if (activity.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            locationListener = createLocationListener(callback)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_INTERVAL, MINIMUM_DISTANCE, createLocationListener(callback))
        }
    }

    fun removeLocationListener() {
        locationManager.removeUpdates(locationListener)
    }

    private fun createLocationListener(callback: (Location?) -> Unit): LocationListener {
        return object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                callback(location)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) {}
        }
    }
}
