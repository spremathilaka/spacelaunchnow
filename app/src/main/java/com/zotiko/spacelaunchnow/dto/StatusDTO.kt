package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusDTO(
    val name: String
) : Parcelable {

    fun getDisplayStatus(): String = when (name) {
        "TBD" -> "Launch Date Unconfirmed"
        "Go" -> "Go For Launch"
        else -> {
            name
        }
    }
}

