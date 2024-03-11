package com.example.openmind.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.domain.model.post.Post
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

open class GlobalViewModel: ViewModel(),Searchable {
    private val globalState:GlobalViewState = GlobalViewState()
    override fun searchPost(query: String): StateFlow<List<Post>> {
        return globalState.repository.findPostBySubstring(query).stateIn(scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList())
    }
    override fun updateSearchBarVisibility(isVisible:Boolean){
        globalState.isSearchBarVisible.value = isVisible
    }
    override fun isSearchBarVisible():Boolean = globalState.isSearchBarVisible.value

}