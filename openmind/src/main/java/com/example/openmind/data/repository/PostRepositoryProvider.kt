package com.example.openmind.data.repository

object PostRepositoryProvider {
    private val repository = PostRepository()

    fun provideRepository(): PostRepository = repository
}