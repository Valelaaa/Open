package com.example.openmind.ui.search_result.viewModel

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.enums.PostCategories
import com.example.openmind.ui.GlobalViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchResultViewModel : GlobalViewModel() {
    private val _viewState = mutableStateOf(SearchResultViewState())
    val viewState get() = _viewState.value
    fun getSearchResults() = viewState.searchResultList

    fun searchWithQuery(query: String, category: String) {
        GlobalScope
            .launch {
                viewState.searchResultList.clear()
                viewState.repository.findByTitle(query, PostCategories.valueOf(category))
                    .catch { cause: Throwable -> handleError(cause) }
                    .collect {
                        viewState.searchResultList.addAll(it)
                    }
            }
    }


}