package com.example.openmind.di.repository

import com.example.openmind.data.repository.RatingRepository

object RatingRepositoryProvider {
    private val ratingRepository = RatingRepository()
    fun provideRepository(): RatingRepository = ratingRepository

}
