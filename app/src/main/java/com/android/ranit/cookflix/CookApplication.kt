package com.android.ranit.cookflix

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CookApplication : Application() {
    private val tag: String? = CookApplication::class.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "CookApplication onCreate() called")
    }
}