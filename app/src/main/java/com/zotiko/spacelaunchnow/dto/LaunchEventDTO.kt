package com.zotiko.spacelaunchnow.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchEventDTO(
    val id: String,
    val name: String,
    val windowStart: String,
    val windowEnd: String,
    val mission: MissionDTO,
    val launchPad: LaunchPadDTO,
    val rocket: RocketDTO,
    val imageUrl: String,
    val status: StatusDTO,
    var isFavourite: Boolean = false
) : Parcelable

