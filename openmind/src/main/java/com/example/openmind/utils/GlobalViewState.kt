package com.example.openmind.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.post.Post
import kotlinx.coroutines.runBlocking

open class GlobalViewState {
    val repository = PostRepositoryProvider.provideRepository()
    var requestParams: MutableState<RequestParams> = mutableStateOf(repository.getFetchParams())

    fun fetchPosts(): MutableList<Post> {
        val loadedPosts = mutableListOf<Post>()

        runBlocking {
            repository.getData().collect { loadedPosts.addAll(it) }
        }
        return loadedPosts
    }
}
