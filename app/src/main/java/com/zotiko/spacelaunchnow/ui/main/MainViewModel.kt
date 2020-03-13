package com.zotiko.spacelaunchnow.ui.main

import com.zotiko.spacelaunchnow.di.modules.OBSERVER_ON
import com.zotiko.spacelaunchnow.domain.base.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Named

class MainViewModel(
    private val getUpComingLaunchUseCase: GetUpComingLaunchesUC,
    @Named(OBSERVER_ON) private val observerOn: Scheduler
) : BaseViewModel() {

    init {
        getLaunchEvents()
    }

    private fun getLaunchEvents() {
        addDisposable(
            getUpComingLaunchUseCase.run(GetUpComingLaunchesUC.RequestValues())
                .observeOn(observerOn)
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
