package com.example.openmind.data.viewModel

interface Sortable {
    fun getSortingList(): List<SortType>
    fun setActiveSortType(sortType: SortType)
    fun activeSortType(): SortType
    fun onSelect(): () -> Unit
}
