package com.example.hotnews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotnews.api.response.BaseResponse
import com.example.hotnews.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository): ViewModel() {

    var news = MutableLiveData<BaseResponse>()

    fun getBreakingNews() {
        viewModelScope.launch {
            val response = repository.getBreakingNews()
            news.postValue(response.value)
        }
    }
}