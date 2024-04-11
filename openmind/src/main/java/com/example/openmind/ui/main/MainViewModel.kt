package com.example.openmind.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.provider.ProfileRepositoryProvider
import com.example.openmind.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ProfileRepositoryProvider.provideRepository()

    private val jwtTokenState: MutableStateFlow<String?> = MutableStateFlow(null)

    fun setJwtTokenForCurrentProfile(profileId: String) {
        SessionManager.clearSharedPreferences()
        viewModelScope.launch {
            val token = repository.generateJwtToken(profileId)
            SessionManager.saveJwtToken(token)
            Log.d("MainViewModel", "Fetched Token : ${SessionManager.getJwtToken()} ")
        }

    }

}