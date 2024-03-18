package com.example.openmind.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.mapper.PostMapper
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
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

    private var posts: SnapshotStateList<Post> = mutableStateListOf()

    fun findPostBySubstring(subString: String): List<Post> {
        if (subString.isBlank())
            return emptyList()
        val splitSubStrings = subString.trim().lowercase().split(" ")
        val found = mutableListOf<Post>()
        splitSubStrings.any { splitedSubString ->
            found.addAll(posts.filter { post ->
                post.title.lowercase().contains(splitedSubString)
            })
        }
        return found.toList()
    }

    //TODO(Change List To Page)
    override fun fetchAll(): Flow<List<Post>> {
        Log.d("Repository", "request to fetch Data")
        return flow {
            val response = service.fetchAll().execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.forEach(System.out::println)
                emit(responseBody?.map(mapper::fromDto) ?: emptyList())
            } else {
                //Handling Error
                emit(emptyList())
            }
        }
    }

    override fun fetchById(id: String): Flow<Post> {
        TODO("Not yet implemented")
    }

    override fun setRequestParams(requestParams: RequestParams) {
        this.requestParams = requestParams
        fetchAll()
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams = requestParams

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }

    override fun postData(data: Post): Boolean {
        TODO("Not yet implemented")
    }

    fun updateRating(postId: String, rating: Int) {
        //TODO("REQUEST TO UPDATE POST RATING")
        runBlocking {
            posts
                .first { post: Post -> post.postId == postId }.rating.rating.value += rating
        }
    }
}