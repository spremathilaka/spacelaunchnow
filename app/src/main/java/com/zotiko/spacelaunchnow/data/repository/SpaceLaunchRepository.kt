package com.zotiko.spacelaunchnow.data.repository

import com.zotiko.spacelaunchnow.model.LaunchEvent

interface SpaceLaunchRepository {

    fun getUpComingLaunchList(): List<LaunchEvent>
}