package com.example.openmind.domain.model.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Brush

class CategoryInfo(
    val categoryType: PostCategories,
    val categoryDescription: String? = null,
    var postCount: MutableState<Int> = mutableIntStateOf(0),
    val categoryTitle: String,
    val backgroundStyle: Brush,
    val backgroundImage: Int,
    val tagLine: String = "",
){
    fun setCategoryPostCount(n: Int){
        postCount.value = n
        Log.d("CategoryInfo","New CategoryInfo value = ${postCount.value}")
    }
}