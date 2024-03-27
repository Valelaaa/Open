package com.example.openmind.domain.model.mapper.provider

import com.example.openmind.domain.model.mapper.CreateCommentMapper

object CreateCommentMapperProvider {
    private val mapperInstance = CreateCommentMapper()
    fun provideMapper() = mapperInstance
}