package com.example.openmind.data.post

import java.util.Date
import java.util.UUID

data class Post(
    var title: String,
    var description: String = "",
    val author: String = "Unknown",
    var createdDate: Date = Date(System.currentTimeMillis()),
    var rating: Int = 0,
    var comments: List<UserComment> = listOf(),
) {
    var postId: String = UUID.randomUUID().toString()
    private var commentCount: Int

    init {
        commentCount = deepCommentCount()
    }

    fun getCommentsCount(): Int = commentCount
    private fun deepCommentCount(): Int {
        var count = 0

        for (subComment in comments) {
            count += countComments(subComment)
        }

        return count
    }

    private fun countComments(comment: UserComment): Int {
        var subCommentCount = 1

        for (subComment in comment.subComments) {
            subCommentCount += countComments(subComment)
        }

        return subCommentCount
    }

    fun formatElapsedTime(): String {
//        val nowMillis = Date(System.currentTimeMillis()).time
//        var durationMillis: Long = nowMillis - (createdDate.time)

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

