package com.example.openmind.data.repository

import android.util.Log
import com.example.openmind.domain.api.PostServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesRepository : Repository<PostCategories> {
    private val tag = "CategoriesRepository"
    private val retrofit: Retrofit
    private val service: PostServices
    val postsCount: MutableMap<PostCategories, Int>


    init {
        Log.d(tag, "Repository initialization")
        postsCount = mutableMapOf()
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(PostServices::class.java)
        if (service != null) {
            setCategoriesCount()
        }
    }

    private fun setCategoriesCount() {
        GlobalScope.launch {
            for (category in PostCategories.values()) {
                Log.d(tag, "Initializing post categories count")
                fetchPostCountByCategory(category = category).collect {
                    postsCount[category] = it
                }
            }
        }
    }

    private suspend fun fetchPostCountByCategory(category: PostCategories): Flow<Int> = flow {

        val response =
            service.getPostCountByCategory(category = category.getStringValue()).execute()
        if (response.isSuccessful) {
            emit(response.body() ?: 0)
        } else {
            emit(0)
        }
    }

    override suspend fun fetchAll(): Flow<List<PostCategories>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchById(id: String): Flow<PostCategories> {
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

    override suspend fun postData(data: PostCategories): Boolean {
        TODO("Not yet implemented")
    }


}