package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO

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
) {
    fun toDTO() = LaunchEventDTO(
        name,
        imgUrl,
        mission.toDTO(),
        launchPad.toDTO(),
        rocket.toDTO(),
        imageUrl,
        infoGraphicUrl
    )
}






