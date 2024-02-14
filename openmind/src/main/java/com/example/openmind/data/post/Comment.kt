package com.example.openmind.data.post

import java.util.Date
import java.util.UUID

data class Comment(
    val author: User,
    val message: String,
    val rating: Int = 0,
    val createdDate: Date = Date(System.currentTimeMillis()),
    val modificationDate: Date = Date(System.currentTimeMillis()),
    val subComments: List<Comment> = listOf(),
    val commentId: UUID = UUID.randomUUID()
) {
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

fun countComments(comment: Comment): Int {
    var count = 1

    for (subComment in comment.subComments) {
        count += countComments(subComment)
    }

    return count
}