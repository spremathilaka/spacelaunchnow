package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.MissionDTO

data class Mission(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String
) {
    fun toDTO() = MissionDTO(
        name,
        description,
        type
    )
}