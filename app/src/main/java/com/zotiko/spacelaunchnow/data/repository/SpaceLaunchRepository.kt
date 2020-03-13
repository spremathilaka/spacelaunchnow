package com.zotiko.spacelaunchnow.data.repository

import com.zotiko.spacelaunchnow.model.UpComingLaunches
import io.reactivex.Single

interface SpaceLaunchRepository {

    fun getUpComingLaunchList(): Single<UpComingLaunches>
}