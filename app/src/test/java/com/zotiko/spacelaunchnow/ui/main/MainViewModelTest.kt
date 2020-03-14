package com.zotiko.spacelaunchnow.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zotiko.spacelaunchnow.domain.base.upcominglaunches.GetUpComingLaunchesUC
import com.zotiko.spacelaunchnow.model.UpComingLaunches
import com.zotiko.spacelaunchnow.ui.data.PageErrorState
import com.zotiko.spacelaunchnow.utils.TestUtils
import com.zotiko.spacelaunchnow.utils.any
import com.zotiko.spacelaunchnow.utils.lambdaMock
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.IOException

class MainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchGetUpComingLaunchesUC: GetUpComingLaunchesUC

    private lateinit var viewModel: MainViewModel

    private val viewStateObserver: Observer<UpComingLaunchContract.ViewState> = lambdaMock()

    @Captor
    private lateinit var viewStateCaptor: ArgumentCaptor<UpComingLaunchContract.ViewState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun initViewModel() {
        viewModel = MainViewModel(fetchGetUpComingLaunchesUC, Schedulers.trampoline())
        viewModel.viewState.observeForever(viewStateObserver)
    }

    @Test
    fun `when api success should show result`() {
        // Given
        Mockito.`when`(fetchGetUpComingLaunchesUC.run(any()))
            .thenReturn(getDummyUpComingLaunches())
        initViewModel()
        // When
        viewModel.fetchLaunchEvents()
        // Then
        with(viewStateCaptor) {
            verify(viewStateObserver, times(2)).onChanged(capture())
            assertFalse(value.isLoading)
            assertNotNull(value.activityData)
            assertNull(value.errorState)
        }
    }

    @Test
    fun `network error state is emitted when there is no network`() {
        // Given
        Mockito.`when`(fetchGetUpComingLaunchesUC.run(any()))
            .thenReturn(Single.error(IOException()))
        initViewModel()
        // When
        viewModel.fetchLaunchEvents()
        // Then
        with(viewStateCaptor) {
            verify(viewStateObserver, times(2)).onChanged(capture())
            assertEquals(
                UpComingLaunchContract.ViewState(
                    isLoading = false,
                    errorState = PageErrorState.NO_NETWORK
                ), value
            )
        }
    }

    @After
    fun tearDown() {
        viewModel.viewState.removeObserver(viewStateObserver)
    }

    private fun getDummyUpComingLaunches(): Single<GetUpComingLaunchesUC.ResponseValue> {
        val data = TestUtils.loadData(
            "json/getLaunchList_whenSuccess.json",
            UpComingLaunches::class.java
        )
        return Single.just(GetUpComingLaunchesUC.ResponseValue(data!!.launchEvents))
    }
}