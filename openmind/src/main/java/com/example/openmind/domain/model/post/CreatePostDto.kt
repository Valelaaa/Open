package com.example.openmind.domain.model.post

import java.util.UUID

data class CreatePostDto(
    var title: String = "",
    var description: String? = null,
    val category: String
)