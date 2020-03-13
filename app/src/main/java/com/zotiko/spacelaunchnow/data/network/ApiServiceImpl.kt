package com.zotiko.spacelaunchnow.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceImpl {

    private val apiClient = OkHttpClient().newBuilder()
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(apiClient)
        .baseUrl("https://spacelaunchnow.me")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val spaceLaunchApi: ApiService = retrofit().create(ApiService::class.java)
}
