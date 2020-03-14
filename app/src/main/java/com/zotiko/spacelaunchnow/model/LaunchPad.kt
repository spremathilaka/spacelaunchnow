package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.LaunchPadDTO

data class LaunchPad(
    @SerializedName("location")
    val location: PadLocation
) {
    fun toDTO() = LaunchPadDTO(
        location.toDTO()
    )
}