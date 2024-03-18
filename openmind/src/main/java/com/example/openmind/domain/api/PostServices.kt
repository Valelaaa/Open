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
        @Query("sortType") sortType: SortType?,
        @Query("sortOrder") sortBy: SortBy?,
        @Query("pageNumber") currentPage: Int?,
        @Query("pageSize") pageSize: Int?
    ): Call<List<PostDto>>

    @GET("test")
    fun test():Call<String>
    @GET("api/posts")
    fun fetchAll(): Call<List<PostDto>>

    @GET("api/people")
    fun fetchPeople(): Call<String>

    @GET("api/posts/{postId}")
    fun fetchById(@Path("postId") postId: String): Call<Post>

    @POST("api/posts")
    fun updateOrCreate(@Body post: Post): Call<Post>
}