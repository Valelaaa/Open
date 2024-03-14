package com.example.openmind.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.theme.LightGreenGradient
import com.example.openmind.ui.theme.OrangeGradient
import kotlinx.coroutines.runBlocking

open class GlobalViewState {
    val repository = PostRepositoryProvider.provideRepository()
    var requestParams: MutableState<RequestParams> = mutableStateOf(repository.getFetchParams())
    val categoriesList: List<CategoryInfo> = listOf(
        CategoryInfo(
            categoryType = PostCategories.BUG,
            categoryTitle = "Report bugs",
            categoryDescription = "Reports of bugs and issues with the app.",
            postCount = fetchPosts().filter { it.category == PostCategories.BUG }.size,
            backgroundStyle = LightGreenGradient
        ),
        CategoryInfo(
            categoryType = PostCategories.FEATURE,
            categoryTitle = "Recommend features",
            categoryDescription = "New features and improvements to the app.",
            postCount = fetchPosts().filter { it.category == PostCategories.FEATURE }.size,
            backgroundStyle = OrangeGradient
        )
    )

    fun fetchPosts(): MutableList<Post> {
        val loadedPosts = mutableListOf<Post>()

        runBlocking {
            repository.getData().collect { loadedPosts.addAll(it) }
        }
        return loadedPosts
    }
}
