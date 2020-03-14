package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchPadDTO(
    val location: PadLocationDTO
) : Parcelable

