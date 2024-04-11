package com.example.openmind.data.repository

import android.util.Log
import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import com.example.openmind.utils.WebClientUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PostRepository {
    private val service: PostServices = WebClientUtils.createService(PostServices::class.java)

    fun fetchAllSuspend(
        category: PostCategories? = null,
        sortType: SortType? = null,
        sortBy: SortBy? = null,
    ): Flow<List<ShortPostDto>> {
        return flow {
            kotlin.runCatching {
                val response = service.fetchAllSuspend(category.toString(), sortType, sortBy)
                response
            }.onFailure { it ->
                Log.e("PostRepository", "Error in FetchAllSuspend")
                it.printStackTrace()
            }.onSuccess {
                emit(it)
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


    suspend fun fetchPostById(id: String): PostDto {
        return service.fetchById(id)
    }


    fun postData(data: CreatePostDto): Boolean {
        val response = service.createPost(data).execute()

        return response.isSuccessful
    }

}
