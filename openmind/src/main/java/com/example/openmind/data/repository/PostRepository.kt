package com.example.openmind.data.repository

import com.example.openmind.domain.api.post.PostServices
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import com.example.openmind.utils.WebClientUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PostRepository : Repository<Post> {
    private val service: PostServices = WebClientUtils.createService(PostServices::class.java)
    suspend fun fetchAll(
        category: PostCategories? = null,
        sortType: SortType? = null,
        sortBy: SortBy? = null,
    ): Flow<List<ShortPostDto>> {
        return flow {
            val response = service.fetchAll(
                category = category.toString(),
                sortType = sortType, sortBy = sortBy,
            ).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val newPosts = responseBody ?: emptyList()
                emit(newPosts)
            } else {
                emit(emptyList())
            }
        }
    }

    suspend fun findByTitle(
        title: String,
        category: PostCategories?,
        sortType: SortType? = null,
        sortBy: SortBy? = null
    ): Flow<List<ShortPostDto>> {
        return flow {
            val response = service.findBySubString(
                title,
                category = category,
                sortType = sortType,
                sortBy = sortBy
            ).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val filteredPosts = responseBody ?: emptyList()
                emit(filteredPosts)
            } else {
                emit(emptyList())

            }
        }
    }


    suspend fun fetchPostByIdFlow(id: String): Flow<PostDto> = flow {
        val response = service.fetchByIdAsCallable(id).execute()
        if (response.isSuccessful) {
            emit(response.body() ?: PostDto(title = "can not load post"))
        } else {
            emit(PostDto(title = "Can not load post"))
        }
    }

    suspend fun fetchPostById(id: String): PostDto {
        return service.fetchById(id)
    }

    override suspend fun fetchById(id: String): Flow<Post> {
        TODO("Not yet implemented")
    }


    fun postData(data: CreatePostDto): Boolean {
        val response = service.createPost(data).execute()

        return response.isSuccessful
    }
}