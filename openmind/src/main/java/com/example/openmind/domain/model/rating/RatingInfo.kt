package com.example.openmind.domain.model.rating

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import java.util.UUID

data class RatingInfo(
    var ratingId: String = UUID.randomUUID().toString(), //id for entity
    var rating: MutableState<Int> = mutableIntStateOf(0), // текущий рейтинг
    var isRated: MutableState<Int> = mutableIntStateOf(0)//rating state 1 - liked, -1 - disliked, 0 - doesn't rated
)