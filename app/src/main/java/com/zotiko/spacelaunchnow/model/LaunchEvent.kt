package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO
import com.zotiko.spacelaunchnow.ui.extension.DATE_FORMAT_EE_DD_MM_YYYY_HH_MM_A
import com.zotiko.spacelaunchnow.ui.extension.format
import java.util.Date

data class LaunchEvent(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("window_start")
    val windowStart: Date,
    @SerializedName("window_end")
    val windowEnd: Date,
    @SerializedName("mission")
    val mission: Mission,
    @SerializedName("pad")
    val launchPad: LaunchPad,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("status")
    val status: Status,
    @SerializedName("image_url")
    val imageUrl: String?
) {
    fun toDTO() = LaunchEventDTO(
        id = id,
        name = name,
        windowStart = windowStart.format(DATE_FORMAT_EE_DD_MM_YYYY_HH_MM_A),
        windowEnd = windowEnd.format(DATE_FORMAT_EE_DD_MM_YYYY_HH_MM_A),
        mission = mission.toDTO(),
        launchPad = launchPad.toDTO(),
        rocket = rocket.toDTO(),
        imageUrl = imageUrl ?: "",
        status = status.toDTO()
    )
}







