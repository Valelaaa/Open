package com.example.openmind.data.repository

import android.util.Log
import com.example.openmind.domain.api.ApiConfigs.Companion.BASE_URL
import com.example.openmind.domain.api.category.CategoryServices
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.CategoryDto
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class CategoriesRepository : Repository<PostCategories> {
    private val tag = "CategoriesRepository"
    private val retrofit: Retrofit
    private val service: CategoryServices
    var client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()
    val postsCount: MutableMap<PostCategories, Int>

    init {
        Log.d(tag, "Repository initialization")
        postsCount = mutableMapOf()
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(CategoryServices::class.java)
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

    private fun fetchPostCountByCategory(category: PostCategories): Flow<Int> = flow {

        val response =
            service.getPostCount(category = category.getStringValue()).execute()
        if (response.isSuccessful) {
            emit(response.body() ?: 0)
        } else {
            emit(0)
        }
    }

    fun fetchAll(): Flow<List<CategoryDto>> {
        return flow {
            val response = service.getAll(
            ).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                val categories = responseBody ?: emptyList()
                emit(categories)
            } else {
                emit(emptyList())
            }
        }
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

//    override suspend fun postData(data: PostCategories): Boolean {
//        TODO("Not yet implemented")
//    }


}