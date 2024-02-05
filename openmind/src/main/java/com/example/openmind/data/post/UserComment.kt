package com.example.openmind.data.post

import java.time.LocalDateTime

data class UserComment(
    val author: User,
    val message: String,
    val rating: Int = 0,
    val createdDateTime: LocalDateTime = LocalDateTime.now(),
    val editedDateTime: LocalDateTime = LocalDateTime.now(),
    val subComments: List<UserComment> = listOf()
)

fun countComments(comment: UserComment): Int {
    var count = 1

    for (subComment in comment.subComments) {
        count += countComments(subComment)
    }

    return count
}