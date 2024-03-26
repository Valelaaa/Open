package com.example.openmind.data.repository

import com.example.openmind.domain.api.ApiConfigs
import com.example.openmind.domain.api.CommentsServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentsRepository : Repository<CommentDto> {
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiConfigs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: CommentsServices = retrofit.create(CommentsServices::class.java)


    override suspend fun fetchById(id: String): Flow<CommentDto> {
        TODO("Not yet implemented")
    }

    override fun setRequestParams(requestParams: RequestParams) {
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams {
        TODO("Not yet implemented")
    }

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }


    fun fetchCommentsByPostId(currentPostId: String): Flow<List<CommentDto>> {
        return flow {
            val response = service.getCommentsByPostId(currentPostId).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val comments = responseBody ?: emptyList()
                emit(comments)
            } else {
                emit(emptyList())
            }
        }
    }

}
