package com.zotiko.spacelaunchnow.domain.base.upcominglaunches

import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.domain.base.UseCase
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO
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
            val results = it.launchEvents.map { launchEvent ->
                launchEvent.toDTO()
            }.toList()
            ResponseValue(results)
        }
    }

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val upComingLaunchEventList: List<LaunchEventDTO>) : UseCase.ResponseValue
}