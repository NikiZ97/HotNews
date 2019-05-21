package com.example.hotnews.base

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result = safeApiResult(call, errorMessage)
        var data: T? = null

        when(result) {
            is Result.Success -> {
                data = result.data
            }
            is Result.Error -> {
                Log.d("BaseRepository", "Error: $errorMessage, exception: ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(IOException("Error during safeApiResult: $errorMessage"))
        }
    }

    sealed class Result<out T : Any> {
        data class Success<T: Any>(val data: T?): Result<T>()
        data class Error(val exception: Exception): Result<Nothing>()
    }
}