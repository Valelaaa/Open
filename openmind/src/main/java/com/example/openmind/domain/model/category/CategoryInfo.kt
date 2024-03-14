package com.example.openmind.domain.model.category

import androidx.compose.ui.graphics.Brush

class CategoryInfo(
    val categoryType: PostCategories,
    val categoryDescription: String? = null,
    val postCount: Int = 0,
    val categoryTitle: String,
    val backgroundStyle: Brush
)