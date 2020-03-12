package com.zotiko.spacelaunchnow.data.network

import com.zotiko.spacelaunchnow.model.LaunchEvent

interface ApiService {

    fun getUpcomingLaunches(): List<LaunchEvent>
}