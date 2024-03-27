package com.example.openmind.domain.model.mapper

import com.example.openmind.domain.model.comment.CreateCommentModel
import com.example.openmind.domain.model.comment.dto.CreateCommentDto

class CreateCommentMapper : Mapper<CreateCommentModel, CreateCommentDto> {
    override fun toDto(from: CreateCommentModel): CreateCommentDto {
        return CreateCommentDto(
            commentMessage = from.commentMessage,
            postId = from.postId,
            parentCommentId = from.parentCommentId
        )
    }

    override fun fromDto(dto: CreateCommentDto): CreateCommentModel {
        return CreateCommentModel(
            commentMessage = dto.commentMessage,
            postId = dto.postId,
            parentCommentId = dto.parentCommentId
        )
    }
}