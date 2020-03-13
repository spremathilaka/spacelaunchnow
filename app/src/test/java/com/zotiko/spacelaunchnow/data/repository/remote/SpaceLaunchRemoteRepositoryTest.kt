package com.zotiko.spacelaunchnow.data.repository.remote

import com.google.gson.GsonBuilder
import com.zotiko.spacelaunchnow.base.BaseTest
import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.model.UpComingLaunches
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class SpaceLaunchRemoteRepositoryTest : BaseTest() {

    private val apiClient = OkHttpClient().newBuilder().build()
    private val mockApi: ApiService by lazy {
        Retrofit.Builder()
            .client(apiClient)
            .baseUrl(mockServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private lateinit var repository: SpaceLaunchRemoteRepository

    @Before
    fun setUpMock() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }


    @Test
    fun `should return upcoming launches response when api success`() {

        repository = SpaceLaunchRemoteRepository(apiService = mockApi)

        this.mockHttpResponse("json/getLaunchList_whenSuccess.json", HttpURLConnection.HTTP_OK)

        val observable = repository.getUpComingLaunchList()
        val testObserver = TestObserver<UpComingLaunches>()
        observable.subscribeWith(testObserver)

        // then verify
        testObserver.assertSubscribed()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        Assert.assertEquals("result item size", 3, testObserver.values()[0].launchEvents.size)
    }

    @Test
    fun `should return error when api failed`() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        repository = SpaceLaunchRemoteRepository(apiService = mockApi)

        this.mockHttpResponse("json/error.json", HttpURLConnection.HTTP_INTERNAL_ERROR)

        val observable = repository.getUpComingLaunchList()
        val testObserver = TestObserver<UpComingLaunches>()
        observable.subscribeWith(testObserver)

        // then verify
        testObserver.assertSubscribed()
        testObserver.assertNotComplete()
        testObserver.assertError(HttpException::class.java)
    }
    override fun isMockServerEnabled(): Boolean = true
}
