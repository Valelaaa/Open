package com.example.openmind.domain.api.category

import com.example.openmind.domain.model.category.CategoryDto
import com.example.openmind.domain.model.category.PostCategories
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryServices {
    @GET("api/categories")
    fun getAll(): Call<List<CategoryDto>>

    @GET("api/categories/{name}")
    fun getById(@Path("name") categoryName: String): Call<CategoryDto>

    @POST("api/categories/")
    fun createCategory(@Body categoryDto: CategoryDto): Call<Unit>

    @PUT("api/categories/{name}")
    fun updateCategory(@Path("name") name: String, @Body categoryDto: CategoryDto): Call<Unit>

    @DELETE("api/categories/{name}")
    fun deleteCategory(@Path("name") name: String): Call<Unit>

    @GET("api/categories/count")
    fun getPostCount(@Query("category") category:String): Call<Int>
}
