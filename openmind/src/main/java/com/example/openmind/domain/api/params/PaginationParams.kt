package com.example.openmind.domain.api.params

data class PaginationParams (
    val currentPage: Int = 1,
    val pageSize: Int = 10
)