package com.example.openmind.domain.api

import com.example.openmind.enums.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.enums.SortBy
import com.example.openmind.enums.SortType
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostServices {
    @GET("api/posts")
    suspend fun fetchAllSuspend(
        @Query("category") category: String?,
        @Query("sortType") sortType: SortType?,
        @Query("sortOrder") sortBy: SortBy?,
        ): List<ShortPostDto>

    @GET("api/posts/search")
    fun findBySubString(
        @Query("query") query: String,
        @Query("category") category: PostCategories?,
        @Query("sortType") sortType: SortType?,
        @Query("sortOrder") sortBy: SortBy?,
    ): Call<List<ShortPostDto>>

    @GET("api/posts/{postId}")
    suspend fun fetchById(@Path("postId") postId: String): PostDto

    @POST("api/posts")
    fun createPost(@Body post: CreatePostDto): Call<Void>
}
