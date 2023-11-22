package com.example.testapp

import retrofit2.Response

sealed class NetworkState<T> {
    data class Success<T>(val data: T) : NetworkState<T>()
    data class Error<T>(val error: Response<T>) : NetworkState<T>()
}


fun <T> Response<T>.parseResponse(): NetworkState<T> {
    return if (this.isSuccessful && this.body() != null) {
        val responseBody = this.body()
        NetworkState.Success(responseBody!!)
    } else {
        NetworkState.Error(this)
    }
}
