package com.zotiko.spacelaunchnow.data.repository.remote

import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.model.LaunchEvent
import com.zotiko.spacelaunchnow.utils.TestUtil
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenEver

class SpaceLaunchRemoteRepositoryTest {

    @Mock
    private lateinit var fakeApi: ApiService

    private lateinit var repository: SpaceLaunchRemoteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = SpaceLaunchRemoteRepository(
            apiService = fakeApi
        )
    }

    @Test
    fun `should return list of upcoming launches when api success`() {
        val fakeLaunchList = getDummyLaunchEventsList()

        whenEver<Any>(fakeApi.getUpcomingLaunches())
            .thenReturn(fakeLaunchList)

        val upComingLaunchesList = repository.getUpComingLaunchList()
        assertThat(upComingLaunchesList, hasSize(1))
    }

    private fun getDummyLaunchEventsList(): List<LaunchEvent> {
        val dummyList = mutableListOf<LaunchEvent>()
        dummyList.add(TestUtil.getDummyLaunchEvent())
        return dummyList
    }
}