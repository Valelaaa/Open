package com.example.openmind.di.mapper

interface Mapper<F, T> {
    fun toDto(from: F): T
    fun fromDto(dto: T): F
}