package com.example.openmind.domain.model.rating.dto

import com.example.openmind.domain.model.user.UserDto
import java.util.UUID

data class UserVote(
    val id: String = UUID.randomUUID().toString(),
    val user: UserDto = UserDto(),
    val ratingInfo: RatingDto = RatingDto(),
    val vote: Int = 0// 1 - liked, -1 - disliked, 0 - doesn't rated
)
