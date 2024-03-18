package com.example.openmind.data.repository

import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.mapper.PostMapper
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.225.200:3000/"

class PostRepository : Repository<Post> {
    private var requestParams: RequestParams = RequestParams()
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: PostServices = retrofit.create(PostServices::class.java)
    private val mapper: PostMapper = PostMapper()


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
//            found.addAll(posts.value.filter { post ->
//                post.title.lowercase().contains(splitedSubString)
//            })
//        }
//        return found.toList()
//    }

    //TODO(Change List To Page)


    override fun fetchById(id: String): Flow<Post> {
        TODO("Not yet implemented")
    }

    override fun setRequestParams(requestParams: RequestParams) {
        this.requestParams = requestParams
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams = requestParams

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }

    override fun postData(data: Post): Boolean {
        TODO("Not yet implemented")
    }

//    fun updateRating(postId: String, rating: Int) {
//        //TODO("REQUEST TO UPDATE POST RATING")
//        runBlocking {
//            posts.value
//                .first { post: Post -> post.postId == postId }.rating.rating.value += rating
//        }
//    }
}