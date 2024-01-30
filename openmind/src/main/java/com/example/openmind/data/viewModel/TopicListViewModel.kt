package com.example.openmind.data.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.openmind.data.repository.TopicRepository
import com.example.openmind.data.repository.TopicRepositoryImpl
import com.example.openmind.data.topic.Topic

class TopicListViewModel : ViewModel() {
    val topicRepository: TopicRepository = TopicRepositoryImpl()
    var loadedTopics: List<Topic> = topicRepository.getMockTopicList()
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
