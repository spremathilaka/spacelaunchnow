package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PadLocationDTO(
    val name: String,
    val country_code: String
) : Parcelable