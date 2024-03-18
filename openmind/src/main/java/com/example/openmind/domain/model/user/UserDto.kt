package com.example.openmind.domain.model.user

import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.rating.dto.RatingDto
import java.util.UUID

class UserDto(
    val userId: String = UUID.randomUUID().toString(),
    val nickname: String = "nickname",
    val firstName: String = "firstName",
    val lastName: String = "lastName",
    val userPicture: String? = null,
    val posts: List<PostDto> = emptyList(),
    val comments: List<CommentDto> = emptyList(),
    val ratings: List<RatingDto> = emptyList()
)