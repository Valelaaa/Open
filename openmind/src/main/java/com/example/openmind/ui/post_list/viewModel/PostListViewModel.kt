package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.Composable
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.SearchableViewModel
import com.example.openmind.ui.categories.components.getCategoriesInfoList
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostListViewModel : SearchableViewModel(), Sortable {
    private val viewState: PostListViewState = PostListViewState()
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()

    @Composable
    fun getPostList(): List<Post> = viewState.loadedPosts.value

    override fun onSelect(): () -> Unit {
        return {}
    }

    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    fun setActiveCategoryInfo() {
        viewState.activeCategoryInfo =
            getCategoriesInfoList().first { it.categoryType == viewState.activeCategory }
    }

    fun getActiveCategoryInfo(): CategoryInfo = viewState.activeCategoryInfo
    fun getCategoryInitials(): String {
        val words = viewState.activeCategoryInfo.categoryTitle.trim().split(" ")
        val initials = words.map { it.first().uppercase() }
        return initials.joinToString("")
    }

    fun getPostCount(): String {
        return "${viewState.getPostsCount()} posts"
    }

    fun getPostCategory(): PostCategories = viewState.activeCategory ?: PostCategories.BUG
}
