package com.zotiko.spacelaunchnow.ui.main

import androidx.lifecycle.ViewModel
import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.network.ApiServiceImpl
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.data.repository.remote.SpaceLaunchRemoteRepository
import com.zotiko.spacelaunchnow.model.LaunchEvent

class MainViewModel : ViewModel() {

    private val apiService: ApiService = ApiServiceImpl.spaceLaunchApi
    private val repository: SpaceLaunchRepository = SpaceLaunchRemoteRepository(apiService)

    init {
        getLaunchEvents()
    }

    private fun getLaunchEvents(): List<LaunchEvent> {
        repository.getUpComingLaunchList()
        return arrayListOf()
    }
}
