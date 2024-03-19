package com.example.openmind.domain.model.mapper

import androidx.compose.runtime.mutableIntStateOf
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.user.User
import com.example.openmind.domain.model.user.UserDto

class PostMapper : Mapper<Post, PostDto> {
    val commentMapper: CommentMapper = CommentMapper()
    override fun toDto(from: Post): PostDto {
        return PostDto(
            postId = from.postId,
            title = from.title.trim(),
            description = from.description.trim(),
            author = UserDto(nickname = from.author.nickname),
            createdDate = from.createdDate,
            comments = from.comments.map(commentMapper::toDto),
            rating = RatingDto(),
            category = from.category.getStringValue(),
            )
    }

    override fun fromDto(dto: PostDto): Post {
        return Post(
            postId = dto.postId,
            title = dto.title,
            description = dto.description,
            author = User(nickname = dto.author.nickname),
            createdDate = dto.createdDate,
            comments = dto.comments.map(commentMapper::fromDto),
            rating = RatingInfo(
                dto.rating.id,
                rating = mutableIntStateOf(dto.rating.currentRating),
                isRated = mutableIntStateOf(
                    dto.rating.userVote?.vote ?: 0
                )
            ),
            category = PostCategories.valueOf(dto.category)
        )
    }
}