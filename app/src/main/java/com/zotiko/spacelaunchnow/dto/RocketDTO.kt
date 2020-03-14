package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RocketDTO(
    val configuration: RocketConfigurationDTO
) : Parcelable

