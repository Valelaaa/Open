package com.example.openmind.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "session"
    private const val JWT_TOKEN_KEY = "jwtToken"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveJwtToken(jwtToken: String) {
        sharedPreferences.edit().putString(JWT_TOKEN_KEY, jwtToken).apply()
    }

    fun getJwtToken(): String? {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTFlNDU2Ny1lODliLTEyZDMtYTQ1Ni00MjY2MTQxNzQwMDAiLCJpYXQiOjE3MTE5MDExMTYsImV4cCI6MTcxMzExMDcxNn0.r-e_NkS7xP6ZanOW1os8C05WCbyBpVX_JhwHgwy673M"
//        return sharedPreferences.getString(JWT_TOKEN_KEY, null)
    }

    fun hasToken(): Boolean {
        // Проверяем, инициализирован ли sharedPreferences
        if (!::sharedPreferences.isInitialized) {
            throw RuntimeException("SessionManager not initialized. Call init(context) first.")
        }
        return sharedPreferences.contains(JWT_TOKEN_KEY)
    }
}
