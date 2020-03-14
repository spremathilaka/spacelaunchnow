package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RocketConfigurationDTO(

    val url: String,
    val name: String,
    val launchServiceProvider: String
) : Parcelable
