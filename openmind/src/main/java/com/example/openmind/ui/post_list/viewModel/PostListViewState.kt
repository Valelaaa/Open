package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
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


        var loadedPosts: MutableState<List<Post>> = mutableStateOf(emptyList())
    fun fetchList(postCategories: PostCategories): MutableStateFlow<List<Post>> {
        val posts = MutableStateFlow<List<Post>>(listOf())
        GlobalScope.launch {

            repository.fetchAll(postCategories).collect {
                loadedPosts.value = it
//                posts = it
            }
        }
        return posts
    }


    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        var newParams = repository.getFetchParams()
        newParams.sortType = sortType
        repository.setRequestParams(newParams)
        activeSortType.value = sortType
        //TODO(REQUEST TO FETCH LIST)s
    }

    fun getActiveSortType(): SortType = activeSortType.value

    fun getPostsCount(): Int =
        loadedPosts.value?.filter { it.category == activeCategory }?.size ?: 0
}
