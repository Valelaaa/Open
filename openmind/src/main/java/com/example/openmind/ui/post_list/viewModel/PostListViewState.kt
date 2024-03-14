package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.MutableState
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.utils.SortType

//add dependency injection
class PostListViewState(
    private var requestParams: MutableState<RequestParams>
) {
    lateinit var activeCategory: PostCategories
    lateinit var activeCategoryInfo: CategoryInfo
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        requestParams.value.sortType.value = sortType
        //TODO(REQUEST TO FETCH LIST)
    }

    fun getActiveSortType(): SortType = requestParams.value.sortType.value
}
