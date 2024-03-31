package com.example.openmind.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.provider.ProfileRepositoryProvider
import com.example.openmind.utils.SessionManager
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ProfileRepositoryProvider.provideRepository()


    fun setJwtTokenForCurrentProfile(profileId: String) {
        viewModelScope.launch {
            try {
                if (!SessionManager.hasToken()) {
                    val jwtToken = repository.getJwtTokenByProfileId(profileId)
                    SessionManager.saveJwtToken(jwtToken)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}