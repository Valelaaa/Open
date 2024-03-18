package com.example.openmind.domain.model.mapper

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.domain.model.user.User
import java.util.Date
import java.util.UUID

class CommentMapper : Mapper<Comment, CommentDto> {
    override fun toDto(from: Comment): CommentDto {
        TODO("Not yet implemented")
    }

    override fun fromDto(dto: CommentDto): Comment {

        return Comment(
            message = dto.message,
            author = User("unknown"),
            createdDate = dto.createdDate,
            modificationDate = dto.modificationDate,
            commentId = UUID.fromString(dto.commentId),
            ratingInfo = RatingInfo(
                dto.rating.id,
                rating = mutableStateOf(dto.rating.currentRating),
                isRated = mutableStateOf(
                    dto.rating.userVote?.vote ?: 0
                )
            ),
            parentId = UUID.fromString(dto.parentComment?.commentId ?: UUID.randomUUID().toString()),
            subComments = subcommentsToDto(dto.subComments)
        )
    }

    fun subcommentsToDto(subcomments: List<CommentDto>): List<Comment> {
        val comments = mutableListOf<Comment>()


        subcomments.forEach { dto ->
            comments.add(
                Comment(
                    message = dto.message,
                    author = User("unknown"),
                    createdDate = dto.createdDate,
                    modificationDate = dto.modificationDate,
                    commentId = UUID.fromString(dto.commentId),
                    ratingInfo = RatingInfo(
                        dto.rating.id,
                        rating = mutableStateOf(dto.rating.currentRating),
                        isRated = mutableStateOf(
                            dto.rating.userVote?.vote ?: 0
                        )
                    ),
                    parentId = UUID.fromString(dto.parentComment?.commentId ?: UUID.randomUUID().toString()),
                )
            )
        }
        return comments
    }
}
