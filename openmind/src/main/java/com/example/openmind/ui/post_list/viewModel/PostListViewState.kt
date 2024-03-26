package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.utils.SortType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewState {
    val isLoading = mutableStateOf(true)
    var repository: PostRepository = PostRepositoryProvider.provideRepository()
    lateinit var activeCategoryInfo: CategoryInfo
    private val activeSortType = mutableStateOf(SortType.HOT)
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )
    private var _activeCategory: PostCategories? = null
    var activeCategory: PostCategories?
        get() = _activeCategory
        set(value) {
            _activeCategory = value
        }

    var loadedPosts = mutableStateListOf<ShortPostDto>()
    val isSearchBarVisible = mutableStateOf(false)
    val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    var _searchResults = MutableStateFlow<List<ShortPostDto>>(emptyList())
    val searchResults = _searchResults.asStateFlow()
    fun fetchList(postCategories: PostCategories) {
        GlobalScope.launch {
            isLoading.value = true
            loadedPosts.clear()
            repository.fetchAll(category = postCategories, sortType = activeSortType.value).collect {
                loadedPosts.addAll(it)
                isLoading.value = false
            }
        }
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType

        when (activeSortType.value) {
            SortType.HOT -> loadedPosts.sortWith(compareBy { it.postRating })
            SortType.OLD -> loadedPosts.sortWith(compareBy { it.creationDate })
            SortType.FRESH -> loadedPosts.sortWith(compareByDescending { it.creationDate })
            else -> loadedPosts.sortWith(compareBy<ShortPostDto> { it.postTitle })
        }

    }

    fun getActiveSortType(): SortType = activeSortType.value

    fun getPostsCount(): Int =
        loadedPosts?.filter { it.category == activeCategory?.getStringValue()?.uppercase() }?.size
            ?: 0
}
