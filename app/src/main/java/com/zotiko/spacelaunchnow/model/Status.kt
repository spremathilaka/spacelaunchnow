package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.StatusDTO

data class Status(
    @SerializedName("name")
    val name: String
) {
    fun toDTO() = StatusDTO(
        name
    )
}
