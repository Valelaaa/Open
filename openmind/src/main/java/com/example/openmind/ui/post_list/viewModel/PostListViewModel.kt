package com.example.openmind.ui.post_list.viewModel

import com.example.openmind.utils.SearchableViewModel
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostListViewModel : SearchableViewModel(), Sortable {
    private val viewState: PostListViewState = PostListViewState(getRequestParams())
    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)
    override fun activeSortType(): SortType = viewState.getActiveSortType()

    override fun onSelect(): () -> Unit {
        TODO("Not yet implemented")
        return {}
    }
}
