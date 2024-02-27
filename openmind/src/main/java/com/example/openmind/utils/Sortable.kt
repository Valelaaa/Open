package com.example.openmind.utils

interface Sortable {
    fun getSortingList(): List<SortType>
    fun setActiveSortType(sortType: SortType)
    fun activeSortType(): SortType
    fun onSelect(): () -> Unit
}
