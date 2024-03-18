package com.example.openmind.ui.categories.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val viewState = CategoriesViewState()
    @Composable
    fun getCategoryCountString(category: PostCategories): String {
        return "${viewState.getCategoryCount(category = category).collectAsState().value} posts"
    }

    fun getCategoriesList(): List<CategoryInfo> {
        return viewState.categoriesList
    }
}
