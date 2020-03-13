package com.zotiko.spacelaunchnow.di.modules

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.data.repository.remote.SpaceLaunchRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSpaceLaunchRemoteRepository(apiService: ApiService): SpaceLaunchRepository =
        SpaceLaunchRemoteRepository(apiService)
}