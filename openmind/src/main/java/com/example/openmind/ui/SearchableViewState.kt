package com.example.openmind.ui

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.post.Post

class SearchableViewState : GlobalViewState() {
    val searchResults = mutableStateOf<List<Post>>(emptyList())
    val isSearchBarVisible = mutableStateOf(false)
    val searchText = mutableStateOf("")
}
