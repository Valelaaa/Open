package com.example.openmind.data.repository

import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.mapper.PostMapper
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.225.200:3000/"

class PostRepository : Repository<Post> {
    private var requestParams: RequestParams = RequestParams()
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: PostServices = retrofit.create(PostServices::class.java)
    private var mapper: PostMapper = PostMapper()


    override suspend fun fetchAll(): Flow<List<Post>> {
        return flow {
            val response = service.fetchAll().execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val newPosts = responseBody?.map(mapper::fromDto) ?: emptyList()
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
    ): Flow<List<Post>> {
        return flow {
            val response = service.findBySubString(
                title,
                category = category,
                sortType = sortType,
                sortBy = sortBy
            ).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val filteredPosts = responseBody?.map(mapper::fromDto) ?: emptyList()
                emit(filteredPosts)
            } else {
                emit(emptyList())

            }
        }
    }

    suspend fun fetchAll(
        category: PostCategories? = null,
        sortBy: SortBy? = null,
        sortType: SortType? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<List<Post>> {
        return flow {
            val response = service.fetchAll(
                category = category,
                sortBy = sortBy,
                sortType = sortType,
                currentPage = page,
                pageSize = pageSize
            ).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val newPosts = responseBody?.map(mapper::fromDto) ?: emptyList()
                emit(newPosts)
            } else {
                emit(emptyList())
            }
        }
    }

//    fun findPostBySubstring(subString: String): List<Post> {
//        if (subString.isBlank())
//            return emptyList()
//        val splitSubStrings = subString.trim().lowercase().split(" ")
//        val found = mutableListOf<Post>()
//        splitSubStrings.any { splitedSubString ->
//            found.addAll(postListInstance.value.filter { post ->
//                post.title.lowercase().contains(splitedSubString)
//            })
//        }
//        return found.toList()
//    }


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

    override suspend fun postData(data: Post): Boolean {
        val response = service.updateOrCreate(mapper.toDto(data)).execute()

        return response.isSuccessful
    }

//    fun updateRating(postId: String, rating: Int) {
//        //TODO("REQUEST TO UPDATE POST RATING")
//        runBlocking {
//            posts.value
//                .first { post: Post -> post.postId == postId }.rating.rating.value += rating
//        }
//    }
}