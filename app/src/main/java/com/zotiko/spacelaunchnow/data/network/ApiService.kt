package com.zotiko.spacelaunchnow.data.network

import com.zotiko.spacelaunchnow.model.UpComingLaunches
import retrofit2.Call
import retrofit2.http.GET

/**
A retrofit Network Interface for the Api
 */
interface ApiService {

    @GET("/api/3.3.0/launch/upcoming/")
    fun getUpcomingLaunches(): Call<UpComingLaunches>
}