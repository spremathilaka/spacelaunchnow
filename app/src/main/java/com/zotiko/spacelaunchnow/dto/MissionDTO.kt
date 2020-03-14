package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MissionDTO(

    val name: String,
    val description: String,
    val type: String
) : Parcelable

