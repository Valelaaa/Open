package com.example.openmind.domain.model.comment.dto

import com.google.gson.annotations.SerializedName

data class CreateCommentDto(
    @SerializedName("commentMessage")
    val commentMessage: String = " message",
    @SerializedName("postId")
    val postId: String?,
    @SerializedName("parentCommentId]")
    val parentCommentId: String? = null,
)