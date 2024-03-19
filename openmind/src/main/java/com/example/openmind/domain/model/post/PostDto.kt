package com.example.openmind.domain.model.post

import androidx.annotation.Keep
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.rating.dto.RatingDto
import com.example.openmind.domain.model.user.UserDto
import com.google.gson.annotations.SerializedName
import java.io.Serial
import java.util.Date
import java.util.UUID
@Keep
data class PostDto(
    @SerializedName("postId")
    val postId: String = UUID.randomUUID().toString(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("author")
    val author: UserDto = UserDto(),
    @SerializedName("createdDate")
    var createdDate: Date = Date(System.currentTimeMillis()),
    @SerializedName("comments")
    var comments: List<CommentDto> = listOf(),
    @SerializedName("rating")
    val rating: RatingDto = RatingDto(postId),
    @SerializedName("category")
    val category: String = PostCategories.BUG.getStringValue()
)
