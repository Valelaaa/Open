package com.example.openmind.ui.categories.components

import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.theme.LightGreenGradient
import com.example.openmind.ui.theme.OrangeGradient

fun getCategoriesInfoList() = listOf(
    CategoryInfo(
        categoryType = PostCategories.BUG,
        categoryTitle = "Report bugs",
        categoryDescription = "Reports of bugs and issues with the app.",
        backgroundStyle = OrangeGradient,
    ),
    CategoryInfo(
        categoryType = PostCategories.FEATURE,
        backgroundStyle = LightGreenGradient,
        categoryTitle = "Recommend features",
        categoryDescription = "New features and improvements to the app.",
    )
)