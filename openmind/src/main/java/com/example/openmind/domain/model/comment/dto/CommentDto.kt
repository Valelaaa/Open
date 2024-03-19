package com.example.openmind.domain.model.comment.dto

import com.example.openmind.domain.model.comment.Comment
import java.util.Date
import java.util.UUID
import com.example.openmind.domain.model.user.UserDto
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.rating.dto.RatingType

data class CommentDto(
    val commentId: String = UUID.randomUUID().toString(),
    val parentComment: CommentDto? = null,
    val message: String = "",
    val createdDate: Date = Date(System.currentTimeMillis()),
    val user: UserDto = UserDto(),
    val modificationDate: Date = Date(System.currentTimeMillis()),
    val subComments: List<CommentDto> = emptyList(),
    val rating: RatingDto = RatingDto(ratingType = RatingType.COMMENT)
)