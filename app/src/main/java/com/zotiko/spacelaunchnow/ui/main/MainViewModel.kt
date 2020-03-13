package com.zotiko.spacelaunchnow.ui.main

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.network.ApiServiceImpl
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.data.repository.remote.SpaceLaunchRemoteRepository
import com.zotiko.spacelaunchnow.domain.base.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : BaseViewModel() {

    private val apiService: ApiService = ApiServiceImpl.spaceLaunchApi
    private val repository: SpaceLaunchRepository = SpaceLaunchRemoteRepository(apiService)
    private val getUpComingLaunchUseCase: GetUpComingLaunchesUC = GetUpComingLaunchesUC(
        repository,
        Schedulers.io()
    )

    init {
        getLaunchEvents()
    }

    private fun getLaunchEvents() {
        addDisposable(
            getUpComingLaunchUseCase.run(GetUpComingLaunchesUC.RequestValues())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableSingleObserver<GetUpComingLaunchesUC.ResponseValue>() {
                        override fun onSuccess(apiResponse: GetUpComingLaunchesUC.ResponseValue) {
                            Timber.d("Up Coming Events = ${apiResponse.upComingLaunchEventList.size}")
                        }

                        override fun onError(e: Throwable) {
                            Timber.e(e)
                        }
                    }
                )
        )
    }
}
