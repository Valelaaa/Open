package com.example.openmind.domain.api

import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostServices {
    @GET("api/posts")
    fun fetchAll(
        @Query("category") category: PostCategories?,
        @Query("sortType") sortType: SortType? ,
        @Query("sortOrder") sortBy: SortBy?,
        @Query("pageNumber") currentPage: Int?,
        @Query("pageSize") pageSize: Int?
    ): Call<List<PostDto>>

    @GET("api/posts/search")
    fun findBySubString(
        @Query("query") query: String,
        @Query("category") category: PostCategories?,
        @Query("sortType") sortType: SortType?,
        @Query("sortOrder") sortBy: SortBy?,
    ): Call<List<PostDto>>

    @GET("api/posts/count")
    fun getPostCountByCategory(@Query("category") category: String): Call<Int>

    @GET("api/posts/post/{postId}")
    fun fetchById(@Path("postId") postId: String): Call<PostDto>

    @POST("api/posts")
    fun updateOrCreate(@Body post: PostDto): Call<Void>
}