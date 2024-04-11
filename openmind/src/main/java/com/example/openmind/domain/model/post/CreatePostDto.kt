package com.example.openmind.domain.model.post

data class CreatePostDto(
    var title: String = "",
    var description: String? = null,
    val category: String
)