package com.example.openmind.domain.model.mapper

interface Mapper<F, T> {
    fun toDto(from: F): T
    fun fromDto(dto: T): F
}