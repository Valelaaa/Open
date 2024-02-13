package com.example.openmind.data.viewModel.postlist

import androidx.lifecycle.ViewModel
import com.example.openmind.data.post.Post
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.SortType
import com.example.openmind.data.viewModel.Sortable

class PostListViewModel : ViewModel(), Sortable {
    //TODO(Inject)
    private val viewState: PostListViewState = PostListViewState()

    fun setActiveCategory(category: Categories) {

    }

    fun getPostList(): MutableList<Post> = viewState.fetchPosts()
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()


    override fun onSelect(): () -> Unit {
        TODO("Not yet implemented")
        return {}
    }
}
