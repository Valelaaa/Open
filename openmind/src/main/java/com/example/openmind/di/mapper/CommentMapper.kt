package com.example.openmind.di.mapper

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.comment.dto.CommentDto

class CommentMapper : Mapper<CommentModel, CommentDto> {
    override fun toDto(from: CommentModel): CommentDto {
        return CommentDto(
            commentId = from.commentId,
            commentAuthor = from.commentAuthor,
            commentMessage = from.commentMessage,
            postId = from.postId ?: "",
            createdDate = from.createdDate,
            parentCommentId = from.parentCommentId,
            ratingId = from.ratingId,
            rating = from.rating,
            subComments = from.subComments?.map { this.toDto(it) },
            isRated = from.isRated
        )
    }

    override fun fromDto(dto: CommentDto): CommentModel {
        return CommentModel(
            commentId = dto.commentId,
            commentAuthor = dto.commentAuthor,
            commentMessage = dto.commentMessage,
            postId = dto.postId,
            createdDate = dto.createdDate,
            parentCommentId = dto.parentCommentId,
            ratingId = dto.ratingId,
            rating = dto.rating,
            subComments = dto.subComments?.map { this.fromDto(it) }?.toMutableStateList()
                ?: mutableStateListOf(),
            isRated = dto.isRated
        )
    }

}
