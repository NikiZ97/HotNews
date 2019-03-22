package com.example.hotnews.api

import com.example.hotnews.api.client.ApiFactory
import com.example.hotnews.api.client.ApiService

class DataManager(val apiService: ApiService) {

    companion object {
        val instance: DataManager
        get() = DataManager(ApiFactory.createApiService(ApiService::class.java))
    }
}


