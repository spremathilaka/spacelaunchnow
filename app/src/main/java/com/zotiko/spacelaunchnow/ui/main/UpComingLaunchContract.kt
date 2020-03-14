package com.zotiko.spacelaunchnow.ui.main

import com.zotiko.spacelaunchnow.model.LaunchEvent
import com.zotiko.spacelaunchnow.ui.data.PageErrorState

interface UpComingLaunchContract {

    data class ViewState(
        val isLoading: Boolean = false,
        val activityData: List<LaunchEvent> = listOf(),
        val errorState: PageErrorState? = null
    )
}