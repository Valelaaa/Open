package com.example.openmind.domain.api

import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/profile/login")
    suspend fun loginUser(@Query("profileId") profileId: String): String
}