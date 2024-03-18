package com.example.openmind.ui.categories.viewModel

import androidx.compose.runtime.mutableIntStateOf
import com.example.openmind.R
import com.example.openmind.data.repository.provider.CategoriesRepositoryProvider
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.theme.LightGreenGradient
import com.example.openmind.ui.theme.OrangeGradient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoriesViewState {
    val repository = CategoriesRepositoryProvider().provideRepository()
    val categoriesList = listOf(
        CategoryInfo(
            categoryType = PostCategories.BUG,
            categoryTitle = "Report bugs",
            tagLine = "Got problems?",
            categoryDescription = "Reports of bugs and issues with the app.",
            postCount = mutableIntStateOf(0),
            backgroundStyle = OrangeGradient,
            backgroundImage = R.drawable.bugs_box
        ),
        CategoryInfo(
            categoryType = PostCategories.FEATURE,
            backgroundStyle = LightGreenGradient,
            categoryTitle = "Recommend features",
            tagLine = "New ideas?",
            categoryDescription = "New features and improvements to the app.",
            postCount = mutableIntStateOf(0),
            backgroundImage = R.drawable.feature_box
        )
    )

    @OptIn(DelicateCoroutinesApi::class)
    fun getCategoryCount(category: PostCategories): MutableStateFlow<Int> {
        val postCount = MutableStateFlow(0)
        GlobalScope.launch {
            repository.fetchPostCountByCategory(category = category).collect {
                postCount.value = it
                println(it)
            }
        }

        return postCount
    }

}
