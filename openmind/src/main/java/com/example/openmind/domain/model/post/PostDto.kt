package com.example.openmind.domain.model.post

import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.user.UserDto
import java.util.Date
import java.util.UUID

data class PostDto(
    val postId: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String = "",
    val author: UserDto = UserDto(),
    var createdDate: Date = Date(System.currentTimeMillis()),
    var comments: List<CommentDto> = listOf(),
    val rating: RatingDto = RatingDto(postId),
    val category: String = PostCategories.BUG.getStringValue()
)
