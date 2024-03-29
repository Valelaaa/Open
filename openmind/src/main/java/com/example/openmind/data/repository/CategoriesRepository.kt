package com.example.openmind.data.repository

import com.example.openmind.domain.api.category.CategoryServices
import com.example.openmind.domain.model.category.CategoryDto
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.WebClientUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CategoriesRepository : Repository<PostCategories> {
    private val retrofit = WebClientUtils.getRetrofitInstance()
    private val service: CategoryServices =
        WebClientUtils.createService(CategoryServices::class.java)


    fun fetchAll(): Flow<List<CategoryDto>> {
        return flow {
            kotlin.runCatching {
                val response = service.fetchAllSuspend()
                response
            }.onFailure {
                println("!!! onError")
                it.printStackTrace()
            }.onSuccess {
                emit(it)
            }
        }
    }

    fun fetchAllSuspend(): Flow<List<CategoryDto>> {
        return flow {
            kotlin.runCatching {
                service.fetchAllSuspend()
            }.onSuccess {
                emit(it)
            }.onFailure {
                emit(emptyList())
            }
        }
    }

    override suspend fun fetchById(id: String): Flow<PostCategories> {
        TODO("Not yet implemented")
    }


}