package com.example.openmind.domain.model.post

import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.utils.Rateable
import java.util.Date
import java.util.UUID

open class Post(
    val postId: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String = "",
    val author: String = "unknown",
    var createdDate: Date = Date(System.currentTimeMillis()),
    var comments: List<Comment> = listOf(),
    val rating: RatingInfo = RatingInfo(postId),
    val category: PostCategories = PostCategories.BUG
) : Rateable {

    fun getCommentsCount(): Int = deepCommentCount()
    private fun deepCommentCount(): Int {
        var count = 0

        for (subComment in comments) {
            count += countComments(subComment)
        }

        return count
    }

    private fun countComments(comment: Comment): Int {
        var subCommentCount = 1

        for (subComment in comment.subComments) {
            subCommentCount += countComments(subComment)
        }

        return subCommentCount
    }

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

    override fun updateRating(ratingValue: Int) {
        if (ratingValue > rating.rating.value) {
            rating.isRated.value = 1
            rating.rating.value += 1
        } else if (ratingValue < rating.rating.value) {
            rating.isRated.value = -1
            rating.rating.value += 1
        }
    }
}

