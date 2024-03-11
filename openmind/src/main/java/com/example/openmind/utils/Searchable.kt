package com.example.openmind.utils

import com.example.openmind.domain.model.post.Post
import kotlinx.coroutines.flow.StateFlow

interface Searchable {
    fun searchPost(query:String): StateFlow<List<Post>>
    fun updateSearchBarVisibility(isVisible:Boolean)
    fun isSearchBarVisible():Boolean
}
