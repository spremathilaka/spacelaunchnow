package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.RocketConfigurationDTO

data class RocketConfiguration(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("launch_service_provider")
    val launchServiceProvider: String
) {
    fun toDTO() = RocketConfigurationDTO(
        url,
        name,
        launchServiceProvider

    )
}
