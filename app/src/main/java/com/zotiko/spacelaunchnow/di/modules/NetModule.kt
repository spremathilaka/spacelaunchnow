package com.zotiko.spacelaunchnow.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zotiko.spacelaunchnow.data.network.ApiService
import com.zotiko.spacelaunchnow.data.repository.SpaceLaunchRepository
import com.zotiko.spacelaunchnow.domain.upcominglaunches.GetUpComingLaunchesUC
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val READ_TIMEOUT = 30.toLong()
private const val CONNECT_TIMEOUT = 30.toLong()
private const val WRITE_TIMEOUT = 30.toLong()
private const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat(SERVER_DATE_TIME_FORMAT)
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Reusable
    internal fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String, gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUpComingLaunchesUC(
        repository: SpaceLaunchRepository,
        @Named(SUBSCRIBER_ON) backgroundScheduler: Scheduler
    ): GetUpComingLaunchesUC =
        GetUpComingLaunchesUC(
            repository,
            backgroundScheduler
        )
}