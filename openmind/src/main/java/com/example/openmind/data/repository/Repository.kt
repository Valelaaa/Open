package com.example.openmind.data.repository

import com.example.openmind.data.repository.params.RequestParams
import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    fun getData(): Flow<List<T>>
    fun getById(id: String): Flow<T>
    fun postData(data: T): Boolean
    fun setOrderParams(requestParams: RequestParams): Unit
    fun getFetchParams(): RequestParams
    fun updateFetchParams(requestParams: RequestParams): Boolean
}