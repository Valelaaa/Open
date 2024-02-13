package com.example.openmind.data.viewModel.post

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.viewModel.SortType

class PostViewState() {
    private var _activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)

    private val sortingList: List<SortType> = listOf<SortType>(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        _activeSortType.value = sortType
    }

    fun getActiveSortType(): MutableState<SortType> = mutableStateOf(_activeSortType.value)
}