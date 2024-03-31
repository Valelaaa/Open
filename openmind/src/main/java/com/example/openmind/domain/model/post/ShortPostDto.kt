package com.example.openmind.domain.model.post

import java.util.Date

data class ShortPostDto(
        var postId: String,
        var postTitle: String?,
        var postRating: Int? = 0,
        var creatorName: String?,
        var creationDate:Long =  Date(System.currentTimeMillis()).time,
        var isRated: Int = 0,
        var commentsCount: Int = 0,
        var category: String = "",
        var ratingId:String = "",
){
        fun formatElapsedTime(): String {

                val nowMillis = System.currentTimeMillis()
                val createdDateMillis = creationDate
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