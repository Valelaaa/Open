package com.example.openmind.data.repository

import com.example.openmind.domain.api.ApiConfigs
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.api.post.PostServices
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.mapper.PostMapper
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostRepository : Repository<Post> {
    private var requestParams: RequestParams = RequestParams()
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiConfigs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: PostServices = retrofit.create(PostServices::class.java)
    private var mapper: PostMapper = PostMapper()


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


    override suspend fun fetchById(id: String): Flow<Post> = flow {
        val response = service.fetchById(id).execute()
        if (response.isSuccessful) {
            emit(mapper.fromDto(response.body() ?: PostDto(title = "can not load post")))

        } else {
            emit(Post(title = "Can not load post"))
        }
    }

    override fun setRequestParams(requestParams: RequestParams) {
        this.requestParams = requestParams
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams = requestParams

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }

    fun postData(data: CreatePostDto): Boolean {
        val response = service.createPost(data).execute()

        return response.isSuccessful
    }
}