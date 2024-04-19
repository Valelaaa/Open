package com.example.openmind.utils

import com.example.openmind.enums.SortType

interface Sortable {
    fun getSortingList(): List<SortType>
    fun setActiveSortType(sortType: SortType)
    fun activeSortType(): SortType
}
