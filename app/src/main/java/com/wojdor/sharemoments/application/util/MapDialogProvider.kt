package com.wojdor.sharemoments.application.util

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.ViewGroup.LayoutParams
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapDialogProvider(private val context: Context) {

    companion object {
        private const val MAP_ZOOM = 4.0F
    }

    fun showDialog(longitude: Double, latitude: Double) {
        val mapView = createMapView(latitude, longitude)
        AlertDialog.Builder(context).setView(mapView).create().show()
    }

    private fun createMapView(latitude: Double, longitude: Double): MapView {
        val position = LatLng(latitude, longitude)
        return MapView(context).apply {
            onCreate(null)
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            getMapAsync {
                it.addMarker(createMarkerOptions(position))
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(position, MAP_ZOOM))
                onResume()
            }
        }
    }

    private fun createMarkerOptions(position: LatLng) = MarkerOptions().apply {
        position(position)
        draggable(false)
        visible(true)
    }
}
