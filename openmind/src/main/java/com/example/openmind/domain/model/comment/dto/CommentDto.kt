package com.example.openmind.domain.model.comment.dto

import com.example.openmind.domain.model.comment.Comment
import java.util.Date
import java.util.UUID
import com.example.openmind.domain.model.user.UserDto
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.rating.dto.RatingType
import com.example.openmind.domain.model.user.User
import com.google.gson.annotations.SerializedName

data class CommentDto(
    var commentId: String = UUID.randomUUID().toString(),
    var commentAuthor: String = "",
    var commentMessage: String = " message",
    var postId: String,
    var createdDate: Date = Date(System.currentTimeMillis()),
    var parentCommentId: String? = null,
    var ratingId: String,
    var rating: Int = 0,
    var subComments: List<CommentDto>? = null,

    @SerializedName("isRated")
    var isRated: Int = 0,
){
    fun formatElapsedTime(): String {

        val nowMillis = System.currentTimeMillis()
        val createdDateMillis = createdDate.time
        val durationMillis: Long = nowMillis - createdDateMillis

        val durationInMinutes = durationMillis / 60000
        val durationInDays = durationInMinutes / (60 * 24)

        return when {
            durationInMinutes / 60 < 1 -> String.format("%d m", durationInMinutes)
            durationInDays < 1 -> String.format("%d h", durationInMinutes / 60)
            durationInDays < 7 -> String.format("%d d", durationInDays)
            durationInDays < 30 -> String.format("%d w", durationInDays / 7)
            durationInDays < 365 -> String.format("%d M", durationInDays / 30)
            else -> String.format("%d Y", durationInDays / 365)
        }

    }
}