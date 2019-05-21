package com.example.hotnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotnews.BuildConfig
import com.example.hotnews.api.client.ApiService
import com.example.hotnews.api.response.BaseResponse
import com.example.hotnews.base.BaseRepository

class NewsRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun getBreakingNews(): LiveData<BaseResponse> {
        val data = MutableLiveData<BaseResponse>()
        val response = safeApiCall(
            call = {
                apiService.getBreakingNewsAsync("ru", BuildConfig.API_KEY).await()
            },
            errorMessage = ""
        )

        return setMutableDataValues(response, data)
    }

    private fun setMutableDataValues(response: BaseResponse?, data: MutableLiveData<BaseResponse>)
            : MutableLiveData<BaseResponse> {
        if (response != null) {
            data.value = response
        } else {
            data.value = null
        }
        return data
    }
}