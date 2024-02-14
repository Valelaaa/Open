package com.example.openmind.data.viewModel.postlist

import com.example.openmind.data.post.Post
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.repository.Repository
import com.example.openmind.data.viewModel.SortType
import kotlinx.coroutines.runBlocking

//add dependency injection
class PostListViewState(
    private val repository: Repository<Post> = PostRepositoryProvider.provideRepository()
) {
    private var requestParams = repository.getFetchParams()

    var loadedPosts = mutableListOf<Post>()
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
        requestParams.sortType = sortType
    }

    fun getActiveSortType(): SortType = requestParams.sortType
}
//    private val dataSource =