package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.RocketDTO

data class Rocket(
    @SerializedName("configuration")
    val configuration: RocketConfiguration
) {
    fun toDTO() = RocketDTO(
        configuration.toDTO()
    )
}
