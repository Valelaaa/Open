package com.example.openmind.domain.model.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Brush

class CategoryInfo(
    val categoryType: PostCategories,
    val categoryDescription: String? = null,
    val categoryTitle: String,
    val backgroundStyle: Brush,
)