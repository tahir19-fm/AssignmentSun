package com.hoho.assignmentsun

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentSunApplication:Application() {
    companion object{
        @JvmStatic
        var instance: AssignmentSunApplication? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}