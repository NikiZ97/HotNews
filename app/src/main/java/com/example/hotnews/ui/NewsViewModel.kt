package com.example.hotnews.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hotnews.R
import com.example.hotnews.api.response.BaseResponse
import com.example.hotnews.network.ConnectionManager
import com.example.hotnews.repository.NewsRepository
import kotlinx.coroutines.launch

@Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
class NewsViewModel(private val repository: NewsRepository,
                    private val connectionManager: ConnectionManager,
                    @get: JvmName("getApplication_") private val application: Application): AndroidViewModel(application) {

    val news = MutableLiveData<BaseResponse>()
    val progressState = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        getBreakingNews()
    }

    fun getBreakingNews() {
        viewModelScope.launch {
            if (connectionManager.isNetworkNotAvailable()) {
                showError(application.getString(R.string.no_internet_connection))
            } else {
                progressState.postValue(true)
                val response = repository.getBreakingNews()
                if (response.value != null) {
                    progressState.postValue(false)
                    news.postValue(response.value)
                } else {
                    showError(application.getString(R.string.error_while_loading_news))
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        error.postValue(errorMessage)
    }
}