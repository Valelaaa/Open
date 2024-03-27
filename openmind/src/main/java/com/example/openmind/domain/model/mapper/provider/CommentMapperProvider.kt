package com.example.openmind.domain.model.mapper.provider

import com.example.openmind.domain.model.mapper.CommentMapper

object CommentMapperProvider {
    private val commentMapper = CommentMapper()

    fun provideCommentMapper() = commentMapper
}