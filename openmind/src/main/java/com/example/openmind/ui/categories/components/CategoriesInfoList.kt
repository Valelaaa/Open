package com.example.openmind.ui.categories.components

import androidx.compose.runtime.mutableIntStateOf
import com.example.openmind.R
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.theme.LightGreenGradient
import com.example.openmind.ui.theme.OrangeGradient

fun getCategoriesInfoList() = listOf(
    CategoryInfo(
        categoryType = PostCategories.BUG,
        categoryTitle = "Report bugs",
        tagLine = "Got problems?",
        categoryDescription = "Reports of bugs and issues with the app.",
        postCount = mutableIntStateOf(0),
        backgroundStyle = OrangeGradient,
        backgroundImage = R.drawable.bugs_box
    ),
    CategoryInfo(
        categoryType = PostCategories.FEATURE,
        backgroundStyle = LightGreenGradient,
        categoryTitle = "Recommend features",
        tagLine = "New ideas?",
        categoryDescription = "New features and improvements to the app.",
        postCount = mutableIntStateOf(0),
        backgroundImage = R.drawable.feature_box
    )
)