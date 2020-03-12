package com.zotiko.spacelaunchnow.data.repository.remote

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository

class SpaceLaunchRemoteRepository(private val apiService: ApiService) : SpaceLaunchRepository {

    override fun getUpComingLaunchList() {
        TODO("Not yet implemented")
    }
}