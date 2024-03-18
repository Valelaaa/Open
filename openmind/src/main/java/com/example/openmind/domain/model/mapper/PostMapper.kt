package com.example.openmind.domain.model.mapper

import androidx.compose.runtime.mutableIntStateOf
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.domain.model.user.User

class PostMapper : Mapper<Post, PostDto> {
    val commentMapper: CommentMapper = CommentMapper()
    override fun toDto(from: Post): PostDto {
        TODO("Not yet implemented")
    }

    override fun fromDto(dto: PostDto): Post {
        println(dto)
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
                    dto.rating.userVote?.vote!!
                )
            ),
            category = PostCategories.valueOf(dto.category)
        )
    }
}