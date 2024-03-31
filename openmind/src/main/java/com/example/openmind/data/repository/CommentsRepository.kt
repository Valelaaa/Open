package com.example.openmind.data.repository

import com.example.openmind.domain.api.CommentsServices
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.comment.CreateCommentModel
import com.example.openmind.domain.model.mapper.provider.CommentMapperProvider
import com.example.openmind.domain.model.mapper.provider.CreateCommentMapperProvider
import com.example.openmind.utils.WebClientUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommentsRepository {
    private val service: CommentsServices =
        WebClientUtils.createService(CommentsServices::class.java)


    suspend fun postComment(createCommentModel: CreateCommentModel) {
        val createModelDto = CreateCommentMapperProvider.provideMapper().toDto(createCommentModel)
        service.createPost(createModelDto)
    }

    suspend fun fetchCommentsByPostId(currentPostId: String): List<CommentModel> {
        return service.getCommentsByPostId(currentPostId)
            .map(CommentMapperProvider.provideCommentMapper()::fromDto)
    }


    suspend fun fetchCommentsByPostIdFlow(currentPostId: String): Flow<List<CommentModel>> {
        return flow {
            val response = service.getCommentsByPostIdAsCallable(currentPostId).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val comments = responseBody ?: emptyList()
                val models = comments.map(CommentMapperProvider.provideCommentMapper()::fromDto)
                emit(models)
            } else {
                emit(emptyList())
            }
        }
    }

}
