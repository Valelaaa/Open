package com.example.openmind.data.topic

import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

data class Topic(
    var title: String,
    var description: String = "",
    val author: String = "Unknown",
    val createdDateTime: LocalDateTime = LocalDateTime.now(),
    var editedDateTime: LocalDateTime = LocalDateTime.now(),
    var rating: Int = 0,
    var comments: List<UserComment> = listOf(),
) {
    var topicId: String = UUID.randomUUID().toString()
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
        val now = LocalDateTime.now()
        val duration = Duration.between(createdDateTime, now)


        return when {
            duration.toHours() < 1 -> String.format("%d m", duration.toMinutes())
            duration.toDays() < 1 -> String.format("%d h", duration.toHours())
            duration.toDays() < 7 -> String.format("%d d", duration.toDays())
            duration.toDays() < 30 -> String.format("%d w", duration.toDays() / 7)
            duration.toDays() < 365 -> String.format("%d M", duration.toDays() / 30)
            else -> String.format("%d Y", duration.toDays() / 365)
        }
    }

}

