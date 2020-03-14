package com.zotiko.spacelaunchnow.model

import com.google.gson.annotations.SerializedName
import com.zotiko.spacelaunchnow.dto.PadLocationDTO

data class PadLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("country_code")
    val country_code: String
) {
    fun toDTO() = PadLocationDTO(
        name,
        country_code
    )
}
