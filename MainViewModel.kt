package com.example.testapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage





    val numberList = MutableLiveData<List<MobileNumber>>()

    var job: Job? = null




    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMobileNumbers() {
        loading.value = true

        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val networkState = mainRepository.getAllMobileNumbers()

                withContext(Dispatchers.Main) {
                    when (networkState) {
                        is NetworkState.Success -> {
                            val responseBody = networkState.data
                            numberList.value = responseBody
                        }
                        is NetworkState.Error -> {
                            onError("Error: ${networkState.error.message()}")
                        }
                    }
                    loading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Exception handled: ${e.localizedMessage}")
                    loading.value = false
                }
            }
        }
    }


    private fun onError(message: String) {
        _errorMessage.value = message

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}