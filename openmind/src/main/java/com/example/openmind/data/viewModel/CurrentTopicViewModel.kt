package com.example.openmind.data.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.TopicRepository
import com.example.openmind.data.repository.TopicRepositoryImpl
import com.example.openmind.data.topic.Topic
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CurrentTopicViewModel : ViewModel() {
    private val repository: TopicRepository = TopicRepositoryImpl()
    val currentTopic: MutableState<Topic> = mutableStateOf(repository.getMockTopic())
    val isButtonEnabled = mutableStateOf(false)
    val titleMaxSize = 300
    val textMinSize = 0

    fun updateTitle(newTitle: String) {
        currentTopic.value.title = newTitle
        currentTopic.value.editedDateTime = LocalDateTime.now()
        checkButtonActivity()
    }

    fun updateDescription(newDescription: String) {
        currentTopic.value.description = newDescription
        currentTopic.value.editedDateTime = LocalDateTime.now()
    }

    private fun checkButtonActivity() {
        viewModelScope.launch {
            isButtonEnabled.value = currentTopic.value.title.isNotBlank()
        }
    }

    fun getTopic(): Topic = currentTopic.value
}