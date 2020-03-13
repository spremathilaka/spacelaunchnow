package com.zotiko.spacelaunchnow.data.network

import com.zotiko.spacelaunchnow.BuildConfig
import com.zotiko.spacelaunchnow.data.network.ApiConstants.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceImpl {

    private val TIMER_CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(60)

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(createBaseHttpClient(BuildConfig.DEBUG))
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun createBaseHttpClient(enableLogs: Boolean): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .readTimeout(TIMER_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMER_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)

        if (enableLogs) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.interceptors().add(httpLoggingInterceptor)
        }
        return builder.build()
    }

    val spaceLaunchApi: ApiService = retrofit().create(ApiService::class.java)
}
