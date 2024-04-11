package com.example.openmind.di.repository

import com.example.openmind.data.repository.CommentsRepository

object CommentsRepositoryProvider {
    private val repository = CommentsRepository()

    fun provideRepository(): CommentsRepository = repository
}
