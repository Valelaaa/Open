package com.example.openmind.utils

import androidx.lifecycle.ViewModel
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.post.Post

open class GlobalViewModel : ViewModel() {
    protected val globalState: GlobalViewState = GlobalViewState()

    fun getPostList(): MutableList<Post> = globalState.fetchPosts()
    fun updateRequestParams(requestParams: RequestParams) {
        globalState.requestParams.value = requestParams
    }

    fun getRequestParams() = globalState.requestParams


}