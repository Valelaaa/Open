package com.example.openmind.domain.repository

import com.example.openmind.data.repository.BASE_URL
import com.example.openmind.domain.api.PostServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: PostServices = retrofit.create(PostServices::class.java)
    val result = service.fetchAll()
    val response = result.execute()
    if (response.isSuccessful) {
        val postDtoList = response.body()
        postDtoList?.forEach(System.out::println)
        println(postDtoList)
    } else {
        println("Ошибка: ${response.code()}")
    }
}
