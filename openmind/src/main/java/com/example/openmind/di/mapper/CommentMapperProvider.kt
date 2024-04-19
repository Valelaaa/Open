package com.example.openmind.di.mapper

object CommentMapperProvider {
    private val commentMapper = CommentMapper()

    fun provideCommentMapper() = commentMapper
}