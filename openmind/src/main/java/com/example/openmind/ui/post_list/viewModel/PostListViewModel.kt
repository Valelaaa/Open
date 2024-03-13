package com.example.openmind.ui.post_list.viewModel

import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.SearchableViewModel
import com.example.openmind.utils.PostCategories
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostListViewModel : SearchableViewModel(), Sortable {
    private val viewState: PostListViewState = PostListViewState(getRequestParams())
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()

    override fun getPostList(): List<Post> {
        return super.getPostList().filter{ it.category == viewState.activeCategory }
    }
    override fun onSelect(): () -> Unit {

        return {}
    }

    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    fun getPostCategory(): PostCategories = viewState.activeCategory
}
