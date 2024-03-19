package com.example.openmind.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.domain.model.post.Post
import com.example.openmind.utils.Searchable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class SearchableViewModel : ViewModel(), Searchable {
    private val searchableViewState: SearchableViewState = SearchableViewState()
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    protected var _searchResults = MutableStateFlow<List<Post>>(emptyList())
    val searchResults = _searchResults.asStateFlow()
    override fun searchPost(query: String) {

    }

    fun resetSearch() {
        _searchText.value = ""
        _searchResults.value = emptyList()
    }

    init {
        _searchText
            .debounce(500L)
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .onEach { query ->
                // Выполняем поиск после того, как пользователь перестал печатать в течении 0.5 секунд
                searchPost(query)
            }
            .launchIn(viewModelScope)
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }


    override fun updateSearchBarVisibility(isVisible: Boolean) {
        searchableViewState.isSearchBarVisible.value = isVisible
    }

    override fun isSearchBarVisible(): Boolean = searchableViewState.isSearchBarVisible.value
    override fun onSearch() {
//        TODO("Not yet implemented")
    }

    fun getSearchTextState() = searchableViewState.searchText
//    fun getSearchResults() = searchResults
}