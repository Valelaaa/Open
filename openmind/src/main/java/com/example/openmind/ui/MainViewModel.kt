package com.example.openmind.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.di.repository.ProfileRepositoryProvider
import com.example.openmind.utils.SessionManager
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ProfileRepositoryProvider.provideRepository()

    private var currentProfileId: String = "111e4567-e89b-12d3-a456-426614174000"

    init {
        setJwtTokenForCurrentProfile()
    }

    private fun setJwtTokenForCurrentProfile() {
        SessionManager.clearSharedPreferences()
        viewModelScope.launch {
            val token = repository.generateJwtToken(currentProfileId)
            SessionManager.saveJwtToken(token)
        }

    }

}