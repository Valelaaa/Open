package com.example.openmind.domain.api

import retrofit2.http.POST
import retrofit2.http.Query

interface RatingService {

    @POST("api/rating/upvote")
    suspend fun upvote(@Query("ratingId") ratingId: String)

    @POST("api/rating/downvote")
    suspend fun downvote(@Query("ratingId") ratingId: String)
}
