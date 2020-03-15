package com.zotiko.spacelaunchnow

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.zotiko.spacelaunchnow.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

open class SpaceLaunchApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        this.initDaggerAppComponent()
        setUpPicasso()
    }

    open fun initDaggerAppComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setUpPicasso() {
        val picassoBuilder = Picasso.Builder(this)
        picassoBuilder.downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
        val built = picassoBuilder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = BuildConfig.DEBUG
        Picasso.setSingletonInstance(built)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}