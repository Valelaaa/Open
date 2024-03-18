package com.example.openmind.domain.repository

import com.example.openmind.domain.api.params.RequestParams
import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    fun fetchAll(): Flow<List<T>>
    fun fetchById(id: String): Flow<T>
    fun postData(data: T): Boolean
    fun setRequestParams(requestParams: RequestParams)
    fun getFetchParams(): RequestParams
    fun updateFetchParams(requestParams: RequestParams): Boolean
}