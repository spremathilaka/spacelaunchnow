package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName

data class LaunchEvent(
    @SerializedName("name")
    val name: String,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("mission")
    val mission: Mission,
    @SerializedName("pad")
    val launchPad: LaunchPad,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("infographic_url")
    val infoGraphicUrl: String?
)

data class Rocket(
    @SerializedName("configuration")
    val configuration: RocketConfiguration
)

data class RocketConfiguration(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("launch_service_provider")
    val launchServiceProvider: String
)

data class Mission(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String
)

data class LaunchPad(
    @SerializedName("location")
    val location: PadLocation
)

data class PadLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("country_code")
    val country_code: String
)
