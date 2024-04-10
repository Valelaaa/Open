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
    fun isTokenStateIsNull() = jwtTokenState.value == null

    fun setJwtTokenForCurrentProfile(profileId: String) {
        Log.d("MainViewModel", "Save jwtToken to shared preferences")
        viewModelScope.launch {
            val token = fetchToken(profileId)
            SessionManager.saveJwtToken(token)
            Log.d("MainViewModel", "Fetched Token : ${SessionManager.getJwtToken()} ")
        }

    }

    private suspend fun fetchToken(profileId: String): String {
        return kotlin.run {
            val token = repository.generateJwtToken(profileId)
            token // Return the fetched token
        }
    }
}