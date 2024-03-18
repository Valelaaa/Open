package com.example.openmind.data.repository

import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesRepository : Repository<PostCategories> {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: PostServices = retrofit.create(PostServices::class.java)

    suspend fun fetchPostCountByCategory(category: PostCategories): Flow<Int> = flow {

        val response =
            service.getPostCountByCategory(category = category.getStringValue()).execute()
        if (response.isSuccessful) {
            emit(response.body() ?: 0)
        } else {
            emit(0)
        }
//        val call: Call<Int> = service.getPostCountByCategory(category = category.getStringValue())
//
//        var result = 0
//
//        call.enqueue(object : Callback<Int> {
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                result = response.body() ?: 0
//            }
//
//            override fun onFailure(call: Call<Int>, t: Throwable) {
//                call.cancel()
//            }
//
//        })
//
//        emit(result)
    }

    override suspend fun fetchAll(): Flow<List<PostCategories>> {
        TODO("Not yet implemented")
    }

    override fun fetchById(id: String): Flow<PostCategories> {
        TODO("Not yet implemented")
    }

    override fun setRequestParams(requestParams: RequestParams) {
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams {
        TODO("Not yet implemented")
    }

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }

    override fun postData(data: PostCategories): Boolean {
        TODO("Not yet implemented")
    }


}