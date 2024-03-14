package com.example.openmind.domain.api.params

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.utils.SortBy
import com.example.openmind.utils.SortType

data class RequestParams(
    var postCategories: PostCategories = PostCategories.BUG,
    var sortType: MutableState<SortType> = mutableStateOf(SortType.HOT),
    val sortBy: SortBy = SortBy.DESC,
    val pagination: PaginationParams = PaginationParams(1, 10)
)