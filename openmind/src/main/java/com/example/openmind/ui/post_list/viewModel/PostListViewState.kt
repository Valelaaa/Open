package com.example.openmind.ui.post_list.viewModel

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.utils.SortType

class PostListViewState(
) {
    private var repository: PostRepository = PostRepositoryProvider.provideRepository()
    lateinit var activeCategory: PostCategories
    lateinit var activeCategoryInfo: CategoryInfo
    private val activeSortType = mutableStateOf(SortType.HOT)
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.OLD,
        SortType.FRESH
    )

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        var newParams = repository.getFetchParams()
        newParams.sortType = sortType
        repository.setRequestParams(newParams)
        activeSortType.value = sortType
        //TODO(REQUEST TO FETCH LIST)s
    }

    fun getActiveSortType(): SortType = activeSortType.value
}
