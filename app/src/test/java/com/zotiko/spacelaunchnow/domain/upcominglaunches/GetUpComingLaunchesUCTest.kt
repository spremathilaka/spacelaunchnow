package com.zotiko.spacelaunchnow.domain.upcominglaunches

import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.model.UpComingLaunches
import com.zotiko.spacelaunchnow.utils.TestUtils
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as mockitoWhen

class GetUpComingLaunchesUCTest {

    @Mock
    private lateinit var repository: SpaceLaunchRepository

    private lateinit var useCase: GetUpComingLaunchesUC

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUpComingLaunchesUC(repository, Schedulers.trampoline())
    }

    @Test
    fun `should return list of upcoming launches when api success`() {

        mockitoWhen(repository.getUpComingLaunchList())
            .thenReturn(
                Single.just(
                    TestUtils.loadData(
                        "json/getLaunchList_whenSuccess.json",
                        UpComingLaunches::class.java
                    )
                )
            )
        val observable = useCase.run(GetUpComingLaunchesUC.RequestValues())
        val testObserver = TestObserver<GetUpComingLaunchesUC.ResponseValue>()
        observable.subscribeWith(testObserver)

        // then verify
        testObserver.assertSubscribed()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        Assert.assertEquals(
            "result item size",
            3,
            testObserver.values()[0].upComingLaunchEventList.size
        )
    }
}