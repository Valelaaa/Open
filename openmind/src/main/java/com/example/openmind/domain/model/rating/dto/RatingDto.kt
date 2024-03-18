package com.example.openmind.domain.model.rating.dto

import java.util.UUID

open class RatingDto(
    val id: String = UUID.randomUUID().toString(),
    val currentRating: Int = 0,
    val ratingType: RatingType = RatingType.POST,
    val userVote: UserVote? = null
)