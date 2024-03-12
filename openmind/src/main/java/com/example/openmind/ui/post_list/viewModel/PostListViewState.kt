package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.MutableState
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.utils.SortType

//add dependency injection
class PostListViewState(
    private var requestParams: MutableState<RequestParams>
) {
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )


    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        requestParams.value.sortType = sortType
        //TODO(REQUEST TO FETCH LIST)
    }

    fun getActiveSortType(): SortType = requestParams.value.sortType
}
