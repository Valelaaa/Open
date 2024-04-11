package com.example.openmind.di.repository

import com.example.openmind.data.repository.PostRepository

object PostRepositoryProvider {
    private val repository = PostRepository()

    fun provideRepository(): PostRepository = repository
}