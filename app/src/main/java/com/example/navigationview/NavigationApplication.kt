package com.example.navigationview

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NavigationApplication : Application() {

    companion object {
        lateinit var instance: NavigationApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}