package com.example.openmind.domain.model.comment

data class CreateCommentModel(
    val commentMessage: String = " message",
    var postId: String? = null,
    var parentCommentId: String? = null,
)
