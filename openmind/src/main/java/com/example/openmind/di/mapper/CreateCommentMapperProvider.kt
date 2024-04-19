package com.example.openmind.di.mapper

object CreateCommentMapperProvider {
    private val mapperInstance = CreateCommentMapper()
    fun provideMapper() = mapperInstance
}