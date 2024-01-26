package com.example.openmind.domain.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("")
    @Headers()
    fun postTopic(@Body requestMessage: TopicRequestMessage): Response<PostTopicResponse>
}