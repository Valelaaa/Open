package com.example.openmind.data.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.topic.Topic
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val isButtonEnabled = mutableStateOf(false)
    val titleMaxSize = 300
    val textMinSize = 0

    fun updateTitle(newTitle: String) {
        title.value = newTitle
        checkButtonActivity()
    }

    fun updateDescription(newDescription: String) {
        description.value = newDescription
    }

    private fun checkButtonActivity() {
        viewModelScope.launch {
            isButtonEnabled.value = title.value.isNotBlank()
        }
    }

    fun getTopic(): Topic {
        return Topic(title = title.value, description = description.value)
    }
}