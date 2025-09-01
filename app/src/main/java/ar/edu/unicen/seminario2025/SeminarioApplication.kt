package ar.edu.unicen.seminario2025

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SeminarioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}