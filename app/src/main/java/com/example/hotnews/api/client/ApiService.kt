package com.example.hotnews.api.client

import com.example.hotnews.api.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getBreakingNewsAsync(@Query("country") country: String,
                             @Query("apiKey") apiKey: String): Deferred<Response<BaseResponse>>
}