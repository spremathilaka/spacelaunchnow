package com.zotiko.spacelaunchnow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zotiko.spacelaunchnow.di.modules.OBSERVER_ON
import com.zotiko.spacelaunchnow.domain.base.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.ui.base.BaseViewModel
import com.zotiko.spacelaunchnow.ui.data.PageErrorState
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Named

class MainViewModel(
    private val getUpComingLaunchUseCase: GetUpComingLaunchesUC,
    @Named(OBSERVER_ON) private val observerOn: Scheduler
) : BaseViewModel() {

    private val mutableViewState = MutableLiveData<UpComingLaunchContract.ViewState>()

    val viewState: LiveData<UpComingLaunchContract.ViewState>
        get() = mutableViewState

    fun fetchLaunchEvents() {

        mutableViewState.value = UpComingLaunchContract.ViewState(
            isLoading = true, errorState = null
        )

        addDisposable(
            getUpComingLaunchUseCase.run(GetUpComingLaunchesUC.RequestValues())
                .observeOn(observerOn)
                .subscribeWith(
                    object : DisposableSingleObserver<GetUpComingLaunchesUC.ResponseValue>() {
                        override fun onSuccess(apiResponse: GetUpComingLaunchesUC.ResponseValue) {
                            Timber.d("Up Coming Events = ${apiResponse.upComingLaunchEventList.size}")
                            mutableViewState.value = UpComingLaunchContract.ViewState(
                                isLoading = false,
                                activityData = apiResponse.upComingLaunchEventList
                            )
                        }

                        override fun onError(error: Throwable) {
                            if (error is IOException) {
                                handleLoadingError(PageErrorState.NO_NETWORK)
                            } else if (error is HttpException) {
                                handleLoadingError(PageErrorState.SERVER_ERROR)
                            }
                            Timber.e(error)
                        }
                    }
                )
        )
    }

    private fun handleLoadingError(errorState: PageErrorState) {
        mutableViewState.value = UpComingLaunchContract.ViewState(
            isLoading = false,
            errorState = errorState
        )
    }
}
