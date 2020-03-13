package com.zotiko.spacelaunchnow.domain.base.upcominglaunches

import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.domain.base.UseCase
import com.zotiko.spacelaunchnow.model.LaunchEvent
import io.reactivex.Scheduler
import io.reactivex.Single

class GetUpComingLaunchesUC(
    private val repository: SpaceLaunchRepository,
    backgroundScheduler: Scheduler
) :
    UseCase<GetUpComingLaunchesUC.RequestValues, GetUpComingLaunchesUC.ResponseValue>(
        backgroundScheduler
    ) {
    override fun executeUseCase(requestValues: RequestValues): Single<ResponseValue> {
        return repository.getUpComingLaunchList().map {
            ResponseValue(it.launchEvents)
        }
    }

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val upComingLaunchEventList: List<LaunchEvent>) : UseCase.ResponseValue
}