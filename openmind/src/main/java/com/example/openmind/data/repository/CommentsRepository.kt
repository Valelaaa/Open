package com.example.openmind.data.repository

import com.example.openmind.domain.api.CommentsServices
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.comment.CreateCommentModel
import com.example.openmind.di.mapper.CommentMapperProvider
import com.example.openmind.di.mapper.CreateCommentMapperProvider
import com.example.openmind.utils.WebClientUtils

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

}
