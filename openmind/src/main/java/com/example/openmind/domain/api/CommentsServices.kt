package com.example.openmind.domain.api

import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.comment.dto.CreateCommentDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentsServices {
    @GET("api/comments/postId/{postId}")
    fun getCommentsByPostId(@Path("postId") postId: String): Call<List<CommentDto>>

    @POST("api/comments")
    suspend fun createPost(@Body commentDto: CreateCommentDto)
}
