package com.example.openmind.di.mapper

import com.example.openmind.domain.model.mapper.CreateCommentMapper

object CreateCommentMapperProvider {
    private val mapperInstance = CreateCommentMapper()
    fun provideMapper() = mapperInstance
}