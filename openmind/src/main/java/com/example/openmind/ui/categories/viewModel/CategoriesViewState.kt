package com.example.openmind.ui.categories.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.CategoriesRepository
import com.example.openmind.data.repository.provider.CategoriesRepositoryProvider
import com.example.openmind.domain.model.category.CategoryDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesViewState {
    val loadedCategories = mutableStateListOf<CategoryDto>()
    val isLoading = mutableStateOf(true)
    var repository: CategoriesRepository = CategoriesRepositoryProvider.provideRepository()


    fun fetchList() {
        GlobalScope.launch {
            isLoading.value = true
            loadedCategories.clear()
            repository.fetchAll().collect {
                loadedCategories.addAll(it)
                isLoading.value = false
            }
        }
    }

  init {
  }


}
