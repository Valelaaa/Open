package com.example.openmind.ui.post_list.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.ui.categories.components.getCategoriesInfoList
import com.example.openmind.utils.Searchable
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class PostListViewModel : ViewModel(), Sortable, Searchable {
    private val viewState: PostListViewState = PostListViewState()
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()
    fun getActiveCategory() = viewState.activeCategory
    fun getPostList() = viewState.loadedPosts
    fun postsIsLoading() = viewState.isLoading.value
    fun getSearchResults() = viewState.searchResults
    fun getSearchText() = viewState.searchText

    init {
        viewState._searchText
            .debounce(500L)
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .onEach { query ->
                searchPost(query)
            }
            .launchIn(viewModelScope)
    }

    fun resetSearch() {
        viewState._searchText.value = ""
    }

    fun onSearchTextChanged(text: String) {
        viewState._searchText.value = text
    }


    override fun searchPost(query: String) {
        if (query.isBlank())
            viewState._searchResults.value = emptyList()
        val lowercaseQuery = query.lowercase()
        val subStrings = lowercaseQuery.split(" ")
        val found = mutableListOf<ShortPostDto>()
        subStrings.any { splitedSubString ->
            found.addAll(viewState.loadedPosts.filter { post ->
                post.postTitle!!.lowercase().contains(splitedSubString)
            })
        }
        viewState._searchResults.value = found.toList()
    }

    override fun onSearch() {
        TODO("Not yet implemented")
    }

    override fun updateSearchBarVisibility(isVisible: Boolean) {
        viewState.isSearchBarVisible.value = isVisible
    }

    override fun isSearchBarVisible(): Boolean = viewState.isSearchBarVisible.value


    fun setCategory(postCategories: PostCategories) {
        Log.d(
            "PostListViewModel", "Category set to $postCategories"
        )
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
    fun fetchPostList() {
        viewState.fetchList(postCategories = viewState.activeCategory ?: throw IllegalStateException())
    }
}
