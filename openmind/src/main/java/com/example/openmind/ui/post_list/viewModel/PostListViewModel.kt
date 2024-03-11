package com.example.openmind.ui.post_list.viewModel

import com.example.openmind.domain.model.post.Post
import com.example.openmind.utils.GlobalViewModel
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostListViewModel : GlobalViewModel(), Sortable {
    //TODO(Inject)
    private val viewState: PostListViewState = PostListViewState()


    fun getPostList(): MutableList<Post> = viewState.fetchPosts()
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()


    override fun onSelect(): () -> Unit {
        TODO("Not yet implemented")
        return {}
    }
}
