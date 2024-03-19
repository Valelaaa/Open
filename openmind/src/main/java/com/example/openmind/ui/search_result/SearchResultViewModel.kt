package com.example.openmind.ui.search_result.viewModel

import androidx.lifecycle.ViewModel
import com.example.openmind.domain.model.category.PostCategories
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel(){
    val viewState: SearchResultViewState = SearchResultViewState()
    fun getSearchResults() = viewState.searchResultList

    fun searchWithQuery(query: String, category:String) {
        GlobalScope.launch {
            viewState.searchResultList.clear()
            viewState.repository.findByTitle(query, PostCategories.valueOf(category) ).collect {
                viewState.searchResultList.addAll(it)
            }
        }
    }


}