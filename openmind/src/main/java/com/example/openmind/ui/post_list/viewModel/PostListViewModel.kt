package com.example.openmind.ui.post_list.viewModel

import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.SearchableViewModel
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostListViewModel : SearchableViewModel(), Sortable {
    private val viewState: PostListViewState = PostListViewState()
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()

    override fun getPostList(): List<Post> {
        return super.getPostList().filter { it.category == viewState.activeCategory }
    }

    override fun onSelect(): () -> Unit {

        return {}
    }

    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    fun setActiveCategoryInfo() {
        viewState.activeCategoryInfo =
            getCategoriesList().first { it.categoryType == viewState.activeCategory }
    }

    fun getActiveCategoryInfo(): CategoryInfo = viewState.activeCategoryInfo
    fun getCategoryInitials(): String {
        val words = viewState.activeCategoryInfo.categoryTitle.trim().split(" ")
        val initials = words.map { it.first().uppercase() }
        return initials.joinToString("")
    }

    fun getPostCount(): String {
        return "${viewState.activeCategoryInfo.postCount} posts"
    }

    fun getPostCategory(): PostCategories = viewState.activeCategory
}
