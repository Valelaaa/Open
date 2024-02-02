package com.example.openmind.data.repository

import androidx.compose.runtime.mutableStateOf

object PostRepositoryProvider {
    private val repository = PostRepositoryImpl()

    fun provideRepository():PostRepository = repository
}