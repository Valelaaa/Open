package com.example.openmind.domain.api

import com.example.openmind.domain.model.category.CategoryDto
import retrofit2.http.GET

interface CategoryServices {
    @GET("api/categories")
    suspend fun fetchAllSuspend(): List<CategoryDto>

}
