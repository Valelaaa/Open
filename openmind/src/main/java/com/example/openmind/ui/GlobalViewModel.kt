package com.example.openmind.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class GlobalViewModel : ViewModel() {
    private val _errorMessageState = mutableStateOf("")
    protected val errorMessageState get() = _errorMessageState

    protected open fun handleError(throwable: Throwable) {
        Log.e("GlobalViewModel", "handleError", throwable)
        _errorMessageState.value = throwable.message.toString()

    }

}