package com.zotiko.spacelaunchnow.data.repository.remote

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.model.LaunchEvent
import com.zotiko.spacelaunchnow.model.UpComingLaunches
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SpaceLaunchRemoteRepository(private val apiService: ApiService) : SpaceLaunchRepository {

    override fun getUpComingLaunchList(): List<LaunchEvent> {
        var eventList = listOf<LaunchEvent>()

        apiService.getUpcomingLaunches().enqueue(object : Callback<UpComingLaunches> {
            override fun onResponse(
                call: Call<UpComingLaunches>,
                response: Response<UpComingLaunches>
            ) {
                if (response.isSuccessful) {

                    eventList = response.body()?.launchEvents ?: emptyList()
                    Timber.d("Launch Events ${eventList.size}")
                }
            }

            override fun onFailure(call: Call<UpComingLaunches>, t: Throwable) {
                Timber.e(t)
            }
        })
        Timber.d("Launch Events Results ${eventList.size}")
        return eventList
    }
}
