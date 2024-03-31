package com.example.openmind.domain.model.comment

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

data class CommentModel(
    var commentId: String = UUID.randomUUID().toString(),

    var commentAuthor: String = "",

    var commentMessage: String = " message",

    var postId: String?,

    var createdDate: Long = Date(System.currentTimeMillis()).time,

    var parentCommentId: String? = null,

    var ratingId: String,

    var rating: Int = 0,

    var subComments: SnapshotStateList<CommentModel> = mutableStateListOf(),

    @SerializedName("isRated")
    var isRated: Int = 0,
) {
    fun formatElapsedTime(): String {

        val nowMillis = System.currentTimeMillis()
        val createdDateMillis = createdDate
        val durationMillis: Long = nowMillis - createdDateMillis

        val durationInSeconds = durationMillis / 1000
        val durationInMinutes = durationInSeconds / 60
        val durationInHours = durationInMinutes / 60
        val durationInDays = durationInHours / 24

        return when {
            durationInMinutes < 60 -> String.format("%d m", durationInMinutes)
            durationInHours < 24 -> String.format("%d h", durationInHours)
            durationInDays < 7 -> String.format("%d d", durationInDays)
            durationInDays < 30 -> String.format("%d w", durationInDays / 7)
            durationInDays < 365 -> String.format("%d M", durationInDays / 30)
            else -> String.format("%d Y", durationInDays / 365)
        }
    }
}