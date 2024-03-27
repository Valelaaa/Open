package com.example.openmind.domain.model.comment.dto

data class CreateCommentDto(
    val commentMessage: String = " message",
    val postId: String?,
    val parentCommentId: String? = null,
)