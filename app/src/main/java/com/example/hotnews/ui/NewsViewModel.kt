package com.example.hotnews.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotnews.R
import com.example.hotnews.api.response.BaseResponse
import com.example.hotnews.network.ConnectionManager
import com.example.hotnews.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository,
                    private val connectionManager: ConnectionManager): ViewModel() {

    val news = MutableLiveData<BaseResponse>()
    val error = MutableLiveData<Int>()

    init {
        getBreakingNews()
    }

    fun getBreakingNews() {
        Log.d("TAG", "getBreakingNews()")
        viewModelScope.launch {
            if (connectionManager.isNetworkNotAvailable()) {
                showErrorById(R.string.no_internet_connection)
            } else {
                val response = repository.getBreakingNews()
                news.postValue(response.value)
            }
        }
    }

    private fun showErrorById(stringId: Int) {
        error.postValue(stringId)
    }
}