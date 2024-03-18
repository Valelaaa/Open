package com.example.openmind.data.repository.provider

import com.example.openmind.data.repository.CategoriesRepository

class CategoriesRepositoryProvider {
    private val repository = CategoriesRepository()

    fun provideRepository(): CategoriesRepository = repository
}