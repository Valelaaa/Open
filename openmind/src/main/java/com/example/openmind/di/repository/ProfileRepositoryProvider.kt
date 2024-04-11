package com.example.openmind.di.repository

import com.example.openmind.data.repository.ProfileRepository

object ProfileRepositoryProvider {
    fun provideRepository(): ProfileRepository {
        return profileRepository
    }

    private val profileRepository = ProfileRepository()


}
