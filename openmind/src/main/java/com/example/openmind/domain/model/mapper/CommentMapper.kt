package com.example.openmind.domain.model.mapper

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.rating.dto.RatingType
import com.example.openmind.domain.model.user.User
import com.example.openmind.domain.model.user.UserDto
import java.util.Date
import java.util.UUID

class CommentMapper : Mapper<Comment, CommentDto> {
//    override fun toDto(from: Comment): CommentDto {
//        return CommentDto(
//            message = from.message,
//            createdDate = from.createdDate,
//            user = UserDto(nickname = from.author?.nickname ?: ""),
//            modificationDate = from.modificationDate,
//            subComments = subcommentsFromDto(from.subComments),
//            rating = RatingDto(
//                from.ratingInfo.ratingId,
//                from.ratingInfo.rating.value,
//                ratingType = RatingType.COMMENT
//            ),
//            commentId = from.commentId.toString(),
//            parentComment = CommentDto(commentId = from.parentId.toString()),
//        )
//    }

//    private fun subcommentsFromDto(subComments: List<Comment>): List<CommentDto> {
//        val comments = mutableListOf<CommentDto>()
//
//        subComments.forEach { from ->
//            comments.add(
//                CommentDto(
//                    message = from.message,
//                    createdDate = from.createdDate,
//                    user = UserDto(nickname = from.author?.nickname ?: ""),
//                    modificationDate = from.modificationDate,
//                    subComments = subcommentsFromDto(from.subComments),
//                    rating = RatingDto(
//                        from.ratingInfo.ratingId,
//                        from.ratingInfo.rating.value,
//                        ratingType = RatingType.COMMENT
//                    ),
//                    commentId = from.commentId.toString(),
//                    parentComment = CommentDto(commentId = from.parentId.toString()),
//                )
//            )
//        }
//        return comments
//    }
//
//    override fun fromDto(dto: CommentDto): Comment {
//        val comment = Comment(
//            message = dto.message,
//            author = User(nickname = dto.user.nickname),
//            createdDate = dto.createdDate,
//            modificationDate = dto.modificationDate,
//            commentId = UUID.fromString(dto.commentId),
//            ratingInfo = RatingInfo(
//                dto.rating.id,
//                rating = mutableStateOf(dto.rating.currentRating),
//                isRated = mutableStateOf(
//                    dto.rating.userVote?.vote ?: 0
//                )
//            ),
//            parentId = UUID.fromString(
//                dto.parentComment?.commentId ?: UUID.randomUUID().toString()
//            ),
//            subComments = subcommentsToDto(dto.subComments)
//        )
//        return comment
//    }

//    fun subcommentsToDto(subcomments: List<CommentDto>): List<Comment> {
//        val comments = mutableListOf<Comment>()
//
//        subcomments.forEach { dto ->
//            comments.add(
//                Comment(
//                    message = dto.message,
//                    author = User(nickname = dto.user.nickname),
//                    createdDate = dto.createdDate,
//                    modificationDate = dto.modificationDate,
//                    commentId = UUID.fromString(dto.commentId),
//                    ratingInfo = RatingInfo(
//                        dto.rating.id,
//                        rating = mutableStateOf(dto.rating.currentRating),
//                        isRated = mutableStateOf(
//                            dto.rating.userVote?.vote ?: 0
//                        )
//                    ),
//                    parentId = UUID.fromString(
//                        dto.parentComment?.commentId ?: UUID.randomUUID().toString()
//                    ),
//                )
//            )
//        }
//        return comments
//    }

    override fun toDto(from: Comment): CommentDto {
        TODO("Not yet implemented")
    }

    override fun fromDto(dto: CommentDto): Comment {
        TODO("Not yet implemented")
    }
}
