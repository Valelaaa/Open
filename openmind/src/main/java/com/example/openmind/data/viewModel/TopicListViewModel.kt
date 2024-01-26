package com.example.openmind.data.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.openmind.data.topic.Topic

class TopicListViewModel : ViewModel() {
    var loadedTopics: List<Topic> = listOf(
        Topic("How to manage your money better, daily? Any available lessons?", "description"),
        Topic(
            "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
            "description"
        ),
        Topic(
            "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
            "description"
        ),

        )
    private lateinit var _activeCategory: MutableState<Categories>

    private var _activeSortType: MutableState<SortBy> = mutableStateOf(SortBy.HOT)
    val activeSortType: MutableState<SortBy> = _activeSortType

    fun setActiveSortType(sortType: SortBy) {
        _activeSortType.value = sortType
    }

    fun setActiveCategory(category: Categories) {
        _activeCategory.value = category
    }
}
