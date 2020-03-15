package com.zotiko.spacelaunchnow.di.modules

import com.zotiko.spacelaunchnow.data.network.ApiConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

const val BASE_URL = "base_url"

@Module
class UrlModule {

    @Provides
    @Singleton
    @Named(BASE_URL)
    fun provideBaseUrl(): String {
        return ApiConstants.API_BASE_URL
    }
}