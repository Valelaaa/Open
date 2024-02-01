package com.example.openmind.data.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.PostRepositoryProvider

class PostListViewModel : ViewModel() {
    private val postRepository: PostRepository = PostRepositoryProvider.provideRepository()
    var loadedPosts = postRepository.getMockPostList()
    private lateinit var _activeCategory: MutableState<Categories>

    private var _activeSortType: MutableState<SortBy> = mutableStateOf(SortBy.HOT)
    val activeSortType: MutableState<SortBy> = _activeSortType

    fun setActiveSortType(sortType: SortBy) {
        _activeSortType.value = sortType
    }

    fun setActiveCategory(category: Categories) {
        _activeCategory.value = category
    }
}
