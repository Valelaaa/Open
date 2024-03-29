package com.example.openmind.ui.categories.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.category.CategoryDto

class CategoriesViewState {
    val loadedCategories = mutableStateListOf<CategoryDto>()
    val isLoading = mutableStateOf(true)


}
