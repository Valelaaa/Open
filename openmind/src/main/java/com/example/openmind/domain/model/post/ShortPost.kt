package com.example.openmind.domain.model.post

import java.util.Date
import java.util.UUID

data class ShortPost(
    val postId: String = UUID.randomUUID().toString(),
    val title: String,
    val rating: Int = 0,
    val commentsCount: Int = 0,
    val createdDate: Date = Date(System.currentTimeMillis())
) {
    fun formatElapsedTime(): String {
        val nowMillis = Date(System.currentTimeMillis()).time
        val durationMillis: Long = nowMillis - createdDate.time

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