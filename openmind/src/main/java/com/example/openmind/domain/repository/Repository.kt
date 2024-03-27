package com.example.openmind.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun fetchById(id: String): Flow<T>
}