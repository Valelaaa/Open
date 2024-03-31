package com.example.openmind.data.repository

import com.example.openmind.domain.api.category.CategoryServices
import com.example.openmind.domain.model.category.CategoryDto
import com.example.openmind.utils.WebClientUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CategoriesRepository {
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
    @Deprecated("old realisation")
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


}