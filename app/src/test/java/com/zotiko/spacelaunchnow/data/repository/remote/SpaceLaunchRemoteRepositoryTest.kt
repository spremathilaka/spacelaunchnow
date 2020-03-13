package com.zotiko.spacelaunchnow.data.repository.remote

import com.google.gson.GsonBuilder
import com.zotiko.spacelaunchnow.base.BaseTest
import com.zotiko.spacelaunchnow.data.network.ApiService
import okhttp3.OkHttpClient
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class SpaceLaunchRemoteRepositoryTest : BaseTest() {

    private val apiClient = OkHttpClient().newBuilder().build()
    private val mockApi: ApiService by lazy {
        Retrofit.Builder()
            .client(apiClient)
            .baseUrl(mockServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    private lateinit var repository: SpaceLaunchRemoteRepository

    @Test
    fun `should return list of upcoming launches when api success`() {

        repository = SpaceLaunchRemoteRepository(apiService = mockApi)

        this.mockHttpResponse("json/getLaunchList_whenSuccess.json", HttpURLConnection.HTTP_OK)

        val upComingLaunchesList = repository.getUpComingLaunchList()
        assertThat(upComingLaunchesList, hasSize(3))
    }

    override fun isMockServerEnabled(): Boolean = true
}
