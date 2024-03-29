package com.example.openmind.domain.model.mapper

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.rating.RatingInfo

class PostMapper : Mapper<Post, PostDto> {
    val commentMapper: CommentMapper = CommentMapper()
    override fun toDto(from: Post): PostDto {
        return PostDto(
            postId = from.postId,
            title = from.title.trim(),
            description = from.description.trim(),
            creatorName = from.author,
            createdDate = from.createdDate,
            rating = from.rating.rating.value,
            isRated = from.rating.isRated.value,
            category = from.category.getStringValue(),
        )
    }

    fun toCreatePostDto(from: Post): CreatePostDto {
        return CreatePostDto(
            title = from.title,
            description = from.description,
            category = from.category.getStringValue()
        )
    }

    override fun fromDto(dto: PostDto): Post {
        val rating =
            RatingInfo(isRated = mutableStateOf(dto.isRated), rating = mutableStateOf(dto.rating))
        return Post(
            postId = dto.postId,
            title = dto.title,
            description = dto.description,
            author = dto.creatorName,
            createdDate = dto.createdDate,
            rating = rating,
            category = PostCategories.valueOf(dto.category)
        )
    }
}