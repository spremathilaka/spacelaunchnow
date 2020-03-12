package com.zotiko.spacelaunchnow.model

data class LaunchEvent(
    val name: String,
    val imgUrl: String?,
    val mission: Mission
)

data class Mission(
    val name: String,
    val description: String,
    val type: String
)