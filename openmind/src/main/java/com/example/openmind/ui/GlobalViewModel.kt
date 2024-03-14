package com.example.openmind.ui

import androidx.lifecycle.ViewModel
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.post.Post

open class GlobalViewModel : ViewModel() {
    private val globalState: GlobalViewState = GlobalViewState()

    open fun getPostList(): List<Post> = globalState.fetchPosts()
    fun updateRequestParams(requestParams: RequestParams) {
        globalState.requestParams.value = requestParams
    }

    fun getCategoriesList() = globalState.categoriesList
    fun getRequestParams() = globalState.requestParams
    fun getRepositoryInstance() = globalState.repository
}