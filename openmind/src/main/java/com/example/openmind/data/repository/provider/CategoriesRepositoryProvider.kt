package com.example.openmind.data.repository.provider

import com.example.openmind.data.repository.CategoriesRepository

object CategoriesRepositoryProvider {
    private val repository = CategoriesRepository()

    fun provideRepository(): CategoriesRepository = repository
}