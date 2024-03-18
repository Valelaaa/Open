package com.example.openmind.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.R
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.theme.LightGreenGradient
import com.example.openmind.ui.theme.OrangeGradient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

open class GlobalViewState {
    val repository = PostRepositoryProvider.provideRepository()
    var requestParams: MutableState<RequestParams> = mutableStateOf(repository.getFetchParams())
    private val loadedPosts = fetchPosts()

    val categoriesList: List<CategoryInfo> = listOf(
        CategoryInfo(
            categoryType = PostCategories.BUG,
            categoryTitle = "Report bugs",
            tagLine = "Got problems?",
            categoryDescription = "Reports of bugs and issues with the app.",
            postCount = loadedPosts.filter { it.category == PostCategories.BUG }.size ?: 0,
            backgroundStyle = OrangeGradient,
            backgroundImage = R.drawable.bugs_box
        ),
        CategoryInfo(
            categoryType = PostCategories.FEATURE,
            backgroundStyle = LightGreenGradient,
            categoryTitle = "Recommend features",
            tagLine = "New ideas?",
            categoryDescription = "New features and improvements to the app.",
            postCount = loadedPosts.filter { it.category == PostCategories.FEATURE }.size,
            backgroundImage = R.drawable.feature_box
        )
    )

    fun fetchPosts(): MutableList<Post> {
        Log.d("GlobalViewState", "before fetching")
        GlobalScope.launch {
            repository.fetchAll().collect {
                loadedPosts.addAll(it)
            }
        }
        Log.d("GlobalViewState", "After Fetching")
        return loadedPosts
    }
}
