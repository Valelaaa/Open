package com.example.openmind.domain.model.category

data class CategoryDto(
    var categoryName: String = "",

    var categoryTitle: String = "",

    var categoryImage: String? = null,

    var categoryDescription: String = "",

    var tagLine: String = "",

    var postCount: Long = 0,
)