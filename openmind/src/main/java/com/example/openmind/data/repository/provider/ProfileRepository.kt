package com.example.openmind.data.repository.provider

import android.util.Log
import com.example.openmind.domain.api.UserService
import com.example.openmind.utils.WebClientUtils

class ProfileRepository {
    private val service: UserService = WebClientUtils.createService(UserService::class.java)
    suspend fun generateJwtToken(profileId: String): String {
        Log.d("Profile Repository", "Send request to generate JWTToken")
        return service.loginUser(profileId)
    }
}