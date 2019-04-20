package com.example.hotnews.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotnews.BuildConfig
import com.example.hotnews.api.client.ApiService
import com.example.hotnews.api.response.BaseResponse

class NewsRepository(private val apiService: ApiService) {

    suspend fun getBreakingNews(): LiveData<BaseResponse> {
        val data = MutableLiveData<BaseResponse>()
        val response = apiService.getBreakingNewsAsync("ru", BuildConfig.API_KEY).await()

        Log.d("test", "response: ${response.body()}")

        return if (response.isSuccessful) {
            data.value = response.body()
            data
        } else {
            data.value = null
            data
        }
    }
}