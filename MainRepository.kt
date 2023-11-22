package com.example.testapp

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMobileNumbers() : NetworkState<List<MobileNumber>> {
        val response = retrofitService.getAllMobileNumbers()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}