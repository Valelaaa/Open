package com.example.openmind.domain.api

import com.example.openmind.domain.model.comment.dto.CommentDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentsServices {
    @GET("api/comments/postId/{name}")
    fun getCommentsByPostId(@Path("name") postName: String): Call<List<CommentDto>>

    @POST("api/comments}")
    fun createPost(@Body commentDto: CommentDto): Call<Unit>
}
