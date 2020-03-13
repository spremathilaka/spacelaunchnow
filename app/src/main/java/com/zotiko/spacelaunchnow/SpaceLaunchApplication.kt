package com.zotiko.spacelaunchnow

import android.app.Application
import timber.log.Timber

class SpaceLaunchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}