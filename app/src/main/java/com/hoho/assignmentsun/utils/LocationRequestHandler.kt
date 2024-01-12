package com.hoho.assignmentsun.utils

import android.Manifest
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.hoho.assignmentsun.networkService.ApiResult
import java.util.Locale

class LocationRequestHandler(
    private val context: Context,
    private val onLocationResult: (ApiResult<Address>) -> Unit
) {
    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            try {
                locationResult.lastLocation?.let { location ->

                    val geocoder = Geocoder(context, Locale.getDefault())
                    val list: List<Address> =
                        try {
                            geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            ) as List<Address>
                        } catch (e: Exception) {
                            emptyList()
                        }
                    if (list.isNotEmpty()) {
                        onLocationResult(ApiResult.Success(list[0]))
                        removeLocationUpdates()
                    }
                }
            } catch (e: Exception) {
                onLocationResult(ApiResult.Error(e.message.toString()))
            }
        }
    }

    init {
        requestSingleLocationUpdate()
    }

    private fun requestSingleLocationUpdate() {
        if (checkLocationPermission()) {
            Log.d("initLocal", "requestSingleLocationUpdate: ")
            val locationRequest = createLocationRequest()
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    private fun removeLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun checkLocationPermission(): Boolean {
        return checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PermissionChecker.PERMISSION_GRANTED &&
                checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ) == PermissionChecker.PERMISSION_GRANTED

    }

    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
    }
}
