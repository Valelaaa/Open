package com.example.openmind.domain.model.category

import androidx.compose.ui.graphics.Brush
import com.example.openmind.enums.PostCategories

class CategoryInfo(
    val categoryType: PostCategories,
    val categoryDescription: String? = null,
    val categoryTitle: String,
    val backgroundStyle: Brush,
)