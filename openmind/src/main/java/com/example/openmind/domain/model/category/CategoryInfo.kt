package com.example.openmind.domain.model.category

import androidx.compose.ui.graphics.Brush
import com.example.openmind.R

class CategoryInfo(
    val categoryType: PostCategories,
    val categoryDescription: String? = null,
    val postCount: Int = 0,
    val categoryTitle: String,
    val backgroundStyle: Brush,
    val backgroundImage: Int,
    val tagLine: String = "",
)