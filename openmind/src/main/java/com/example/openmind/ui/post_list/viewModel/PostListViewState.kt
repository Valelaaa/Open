package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.utils.SortType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostListViewState {
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
            if (value != null) {
                fetchList(value)
            }
        }

    var loadedPosts = mutableStateListOf<Post>()

    private fun fetchList(postCategories: PostCategories) {
        GlobalScope.launch {
            loadedPosts.clear()
            repository.fetchAll(postCategories, sortType = activeSortType.value).collect {
                loadedPosts.addAll(it)
            }
        }
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType

        when (activeSortType.value) {
            SortType.HOT -> loadedPosts.sortWith(compareBy { it.rating.rating.value })
            SortType.OLD -> loadedPosts.sortWith(compareBy { it.createdDate })
            SortType.FRESH -> loadedPosts.sortWith(compareByDescending { it.createdDate })
            else -> loadedPosts.sortWith(compareBy<Post> { it.title })
        }

    }

    fun getActiveSortType(): SortType = activeSortType.value

    fun getPostsCount(): Int =
        loadedPosts?.filter { it.category == activeCategory }?.size ?: 0
}
