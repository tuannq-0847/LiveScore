package com.sun.livescore.di

import com.sun.livescore.data.remote.request.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createAppRetrofit() }
}

private const val BASE_URL = "https://livescore-api.com/"

private fun createAppRetrofit() =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
