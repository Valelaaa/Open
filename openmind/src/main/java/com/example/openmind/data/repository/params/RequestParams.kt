package com.example.openmind.data.repository.params

import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.SortBy
import com.example.openmind.data.viewModel.SortType

data class RequestParams(
    var categories: Categories = Categories.BUG,
    var sortType: SortType = SortType.HOT,
    val sortBy: SortBy = SortBy.DESC,
    val pagination: PaginationParams = PaginationParams(1, 10)
)