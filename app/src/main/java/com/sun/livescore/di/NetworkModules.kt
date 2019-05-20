package com.sun.livescore.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sun.livescore.BuildConfig
import com.sun.livescore.data.remote.request.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

val networkModule = module {
    single { createAppRetrofit() }
}

private const val BASE_URL = "https://livescore-api.com/"
private const val TIME_REQUEST = 60L
private const val KEY = "key"
private const val SECRET = "secret"

private fun createAppRetrofit(): ApiService {

    val client = OkHttpClient.Builder()
        .addInterceptor(StethoInterceptor())
        .readTimeout(TIME_REQUEST, SECONDS)
        .writeTimeout(TIME_REQUEST, SECONDS)
        .connectTimeout(TIME_REQUEST, SECONDS)

    client.addInterceptor { chain ->
        val original = chain.request()
        val url = original.url().newBuilder()
            .addQueryParameter(KEY, BuildConfig.API_KEY)
            .addQueryParameter(SECRET, BuildConfig.SECRET_KEY)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()
        return@addInterceptor chain.proceed(request)
    }

    val retrofit = Retrofit.Builder()
        .client(client.build())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}
