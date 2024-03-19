package com.example.openmind.domain.model.comment

import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.domain.model.user.User
import java.util.Date
import java.util.UUID

data class Comment(
    val author: User? = User(""),
    val message: String,
    val createdDate: Date = Date(System.currentTimeMillis()),
    val modificationDate: Date = Date(System.currentTimeMillis()),
    var subComments: List<Comment> = listOf(),
    val commentId: UUID = UUID.randomUUID(),
    val ratingInfo: RatingInfo = RatingInfo(commentId.toString()),
    val parentId: UUID? = null
) {
    constructor(message: String, author: User, subComments: List<Comment>) : this(
        message = message,
        author = author
    ) {
        subComments.forEach { subComment ->
            addSubComment(
                subCommentAuthor = subComment.author ?: User(""),
                subCommentMessage = subComment.message
            )
        }
    }

    fun addSubComment(subCommentMessage: String, subCommentAuthor: User): Comment {
        val subComment = Comment(
            author = subCommentAuthor,
            message = subCommentMessage,
            parentId = this.commentId  // Устанавливаем parentId родительского комментария
        )
        subComments = subComments + subComment
        return this
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

    override fun toString(): String {
        return "Comment(author=$author, message='$message', createdDate=$createdDate, modificationDate=$modificationDate, subComments=$subComments, commentId=$commentId, ratingInfo=$ratingInfo, parentId=$parentId)"
    }

}

fun countComments(comment: Comment): Int {
    var count = 1

    for (subComment in comment.subComments) {
        count += countComments(subComment)
    }

    return count
}