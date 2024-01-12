package com.hoho.assignmentsun

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.hoho.assignmentsun.utils.showToast
import com.hoho.assignmentsun.viewmodels.MainViewModel


open class BaseActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()
    private lateinit var requestLocationPermissions: ActivityResultLauncher<Array<String>>

    fun initLocationPermission(){
        initLocationPermissions()
        if (!checkLocationPermission()) {
            requestForLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return checkSelfPermission(
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ) == PackageManager.PERMISSION_GRANTED

    }

    private fun requestForLocationPermission() {
        requestLocationPermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun initLocationPermissions() {
        requestLocationPermissions = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val isGranted = permissions.all {
                it.value
            }
            if (isGranted) {
                showToast(R.string.permission_granted)
                mainViewModel.getUserLocation(this)
            }
        }
    }
}