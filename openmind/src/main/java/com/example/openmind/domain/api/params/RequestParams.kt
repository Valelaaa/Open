package com.example.openmind.domain.api.params

import com.example.openmind.utils.PostCategories
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType

data class RequestParams(
    var postCategories: PostCategories = PostCategories.BUG,
    var sortType: SortType = SortType.HOT,
    val sortBy: SortBy = SortBy.DESC,
    val pagination: PaginationParams = PaginationParams(1, 10)
)