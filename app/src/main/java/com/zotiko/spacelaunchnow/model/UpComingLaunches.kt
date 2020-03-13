package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName

data class UpComingLaunches(
    @SerializedName("results")
    val launchEvents: List<LaunchEvent>
)