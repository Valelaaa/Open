package com.example.openmind.domain.repository

import com.example.openmind.domain.api.params.RequestParams
import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun fetchById(id: String): Flow<T>
    fun setRequestParams(requestParams: RequestParams)
    fun getFetchParams(): RequestParams
    fun updateFetchParams(requestParams: RequestParams): Boolean
}