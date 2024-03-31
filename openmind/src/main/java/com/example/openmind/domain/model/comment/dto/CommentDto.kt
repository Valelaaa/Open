package com.example.openmind.domain.model.comment.dto

import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

data class CommentDto(
    var commentId: String = UUID.randomUUID().toString(),
    var commentAuthor: String = "",
    var commentMessage: String = " message",
    var postId: String,
    var createdDate: Long = Date(System.currentTimeMillis()).time,
    var parentCommentId: String? = null,
    var ratingId: String,
    var rating: Int = 0,
    var subComments: List<CommentDto>? = null,

    @SerializedName("isRated")
    var isRated: Int = 0,
)