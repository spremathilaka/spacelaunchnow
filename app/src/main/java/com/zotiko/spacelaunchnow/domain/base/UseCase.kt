package com.zotiko.spacelaunchnow.domain.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class UseCase<in Req : UseCase.RequestValues, Res : UseCase.ResponseValue> protected constructor(
    private val backgroundScheduler: Scheduler
) {

    fun run(requestValues: Req): Single<Res> {

        return executeUseCase(requestValues)
            .subscribeOn(backgroundScheduler)
    }

    protected abstract fun executeUseCase(requestValues: Req): Single<Res>

    /**
     * Data passed to a request.
     */
    interface RequestValues

    /**
     * Data received from a request.
     */
    interface ResponseValue
}
