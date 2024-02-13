package com.example.openmind.data.viewModel.postlist

import androidx.compose.runtime.MutableState
import com.example.openmind.data.post.Post
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.repository.Repository
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.SortType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//add dependency injection
class PostListViewState(
    private val repository: Repository<Post> = PostRepositoryProvider.provideRepository()
) {
    private var requestParams = repository.getFetchParams()
    private lateinit var _activeCategory: MutableState<Categories>

    //    private var _activeSortType: MutableState<SortType> = mutableStateOf(requestParams.sortType)
//    val activeSortType: MutableState<SortType> = _activeSortType
    var loadedPosts = mutableListOf<Post>()
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )

    fun fetchPosts(): MutableList<Post> {

        GlobalScope.launch {
            repository.getData().collect { it -> loadedPosts.addAll(it) }
        }
        return loadedPosts
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        requestParams.sortType = sortType
    }

    fun getActiveSortType(): SortType = requestParams.sortType
}
//    private val dataSource =