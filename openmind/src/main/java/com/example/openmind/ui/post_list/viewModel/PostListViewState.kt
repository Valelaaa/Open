package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.post.Post
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.SortType
import kotlinx.coroutines.runBlocking

//add dependency injection
class PostListViewState(
    private val repository: Repository<Post> = PostRepositoryProvider.provideRepository()
) {
    private var requestParams = repository.getFetchParams()
    private var activeSortType = mutableStateOf(requestParams.sortType)
    private var loadedPosts = mutableListOf<Post>()
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )

    fun fetchPosts(): MutableList<Post> {

        runBlocking {
            repository.getData().collect { it -> loadedPosts.addAll(it) }
        }
        return loadedPosts
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType
    }

    fun getActiveSortType(): SortType = activeSortType.value
}
