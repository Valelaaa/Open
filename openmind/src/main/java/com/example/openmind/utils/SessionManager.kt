package com.example.openmind.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

object SessionManager {
    private const val PREF_NAME = "session"
    private const val JWT_TOKEN_KEY = "jwtToken"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveJwtToken(jwtToken: String?) {
        sharedPreferences.edit(commit = true) {
            putString(JWT_TOKEN_KEY, jwtToken)
        }
        Log.d("SessionManager", "Saved Token: ${sharedPreferences.getString(JWT_TOKEN_KEY, null)}")
    }

    fun getJwtToken(): String? {
        val token = sharedPreferences.getString(JWT_TOKEN_KEY, null)
        Log.d("SessionManager", "getJwtToken: $token")
        return token
    }


    fun clearSharedPreferences() {
        Log.d("SessionManager", "jwt token removed")

        sharedPreferences.edit(
            commit = true
        ) {
            remove(JWT_TOKEN_KEY)
        }
        Log.d(
            "SessionManager",
            "Token after remove: ${sharedPreferences.getString(JWT_TOKEN_KEY, null)}"
        )
    }
}
