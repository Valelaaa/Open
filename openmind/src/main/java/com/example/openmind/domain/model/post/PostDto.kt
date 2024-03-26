package com.example.openmind.domain.model.post

import androidx.annotation.Keep
import com.example.openmind.domain.model.category.PostCategories
import com.google.gson.annotations.SerializedName
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
    @SerializedName("creatorName")
    val creatorName: String = "johndoe",
    @SerializedName("createdDate")
    var createdDate: Date = Date(System.currentTimeMillis()),
    @SerializedName("rating")
    val rating: Int = 0,
    @SerializedName("isRated")
    val isRated: Int = 0,
    @SerializedName("category")
    val category: String = PostCategories.BUG.getStringValue(),
    @SerializedName("commentCount")
    val commentCount:Int = 0
)
