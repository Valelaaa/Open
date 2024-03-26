package com.example.openmind.data.repository.provider

import com.example.openmind.data.repository.CommentsRepository

object CommentsRepositoryProvider {
    private val repository = CommentsRepository()

    fun provideRepository(): CommentsRepository = repository
}
