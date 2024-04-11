package com.example.openmind.di.mapper

import com.example.openmind.domain.model.mapper.CommentMapper

object CommentMapperProvider {
    private val commentMapper = CommentMapper()

    fun provideCommentMapper() = commentMapper
}