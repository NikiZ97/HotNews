package com.example.hotnews.api.client

import com.example.hotnews.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private lateinit var httpClient: OkHttpClient.Builder
    private lateinit var builder: Retrofit.Builder

    private val gsonBuilder: GsonBuilder
        get() = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")

    private fun init() {
        val gsonBuilder = gsonBuilder
        builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.serializeNulls().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    fun <X> createApiService(serviceClass: Class<X>): X {
        httpClient = ApiOkHttpClient.create()
        init()
        return builder.client(httpClient.build())
            .build().create(serviceClass)
    }
}