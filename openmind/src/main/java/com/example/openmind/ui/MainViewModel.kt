package com.example.openmind.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.di.repository.ProfileRepositoryProvider
import com.example.openmind.utils.SessionManager
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.RuntimeException
import java.net.SocketTimeoutException

class MainViewModel : GlobalViewModel() {

    private val repository = ProfileRepositoryProvider.provideRepository()

    private var currentProfileId: String = "111e4567-e89b-12d3-a456-426614174000"

    init {
        setJwtTokenForCurrentProfile()
    }

    private fun setJwtTokenForCurrentProfile() {
        SessionManager.clearSharedPreferences()
        viewModelScope.launch {
            try {
                val token = repository.generateJwtToken(currentProfileId)
                SessionManager.saveJwtToken(token)
            } catch (e: IOException) {
                handleError(e)
            }
        }

    }

}