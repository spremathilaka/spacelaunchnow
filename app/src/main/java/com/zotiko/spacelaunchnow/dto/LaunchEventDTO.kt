package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchEventDTO(
    val name: String,
    val imgUrl: String?,
    val mission: MissionDTO,
    val launchPad: LaunchPadDTO,
    val rocket: RocketDTO,
    val imageUrl: String?,
    val infoGraphicUrl: String?
) : Parcelable

