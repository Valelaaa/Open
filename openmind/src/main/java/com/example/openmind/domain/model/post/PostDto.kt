package com.example.openmind.domain.model.post

import androidx.annotation.Keep
import com.example.openmind.domain.model.category.PostCategories
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

@Keep
data class PostDto(
    @SerializedName("postId")
    val postId: String = UUID.randomUUID().toString(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("creatorName")
    val creatorName: String = "johndoe",
    @SerializedName("createdDate")
    var createdDate: Date = Date(System.currentTimeMillis()),
    @SerializedName("rating")
    var rating: Int = 0,
    @SerializedName("isRated")
    var isRated: Int = 0,
    @SerializedName("category")
    val category: String = PostCategories.BUG.getStringValue(),
    @SerializedName("commentCount")
    val commentCount: Int = 0

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

    fun updateRating(ratingValue: Int) {
        if (ratingValue > rating) {
            isRated = 1
            rating += 1
        } else if (ratingValue < rating) {
            isRated = -1
            rating += 1
        }
    }
}
