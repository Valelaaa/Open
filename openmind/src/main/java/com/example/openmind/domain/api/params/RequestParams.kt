package com.example.openmind.domain.api.params

import com.example.openmind.utils.Categories
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType

data class RequestParams(
    var categories: Categories = Categories.BUG,
    var sortType: SortType = SortType.HOT,
    val sortBy: SortBy = SortBy.DESC,
    val pagination: PaginationParams = PaginationParams(1, 10)
)