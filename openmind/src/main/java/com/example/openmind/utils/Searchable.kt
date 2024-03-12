package com.example.openmind.utils

interface Searchable {
    fun searchPost(query: String)
    fun updateSearchBarVisibility(isVisible: Boolean)
    fun isSearchBarVisible(): Boolean
}
