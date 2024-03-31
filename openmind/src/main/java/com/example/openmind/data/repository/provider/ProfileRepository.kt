package com.example.openmind.data.repository.provider

import com.example.openmind.domain.api.UserService
import com.example.openmind.utils.WebClientUtils

class ProfileRepository {
    private val service: UserService = WebClientUtils.createService(UserService::class.java)
    suspend fun getJwtTokenByProfileId(profileId: String):String {
        return service.loginUser(profileId)
    }
}