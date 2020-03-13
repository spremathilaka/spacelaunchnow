package com.zotiko.spacelaunchnow.data.repository.remote

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.model.UpComingLaunches
import io.reactivex.Single

class SpaceLaunchRemoteRepository(private val apiService: ApiService) : SpaceLaunchRepository {

    override fun getUpComingLaunchList(): Single<UpComingLaunches> {
        return apiService.getUpcomingLaunches()
    }
}
