package com.example.openmind.data.repository

import com.example.openmind.domain.api.RatingService
import com.example.openmind.utils.WebClientUtils

class RatingRepository {

    private val service: RatingService = WebClientUtils.createService(RatingService::class.java)

    suspend fun upvote(ratingId: String) {
        return service.upvote(ratingId)
    }

    suspend fun downvote(ratingId: String) {
        return service.downvote(ratingId)
    }

}
