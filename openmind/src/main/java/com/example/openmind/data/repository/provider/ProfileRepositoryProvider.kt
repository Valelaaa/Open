package com.example.openmind.data.repository.provider

object ProfileRepositoryProvider {
    fun provideRepository(): ProfileRepository {
        return profileRepository
    }

    private val profileRepository = ProfileRepository()


}
