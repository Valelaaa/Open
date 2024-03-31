package com.example.openmind.domain.api

import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("api/profile/login")
    suspend fun loginUser(@Query("profileId") profileId: String): String
}