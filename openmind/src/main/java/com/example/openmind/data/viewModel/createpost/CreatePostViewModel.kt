package com.example.openmind.data.viewModel.createpost

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.openmind.data.post.Post

const val tag = "CreatePostViewModel"

class CreatePostViewModel : ViewModel() {
    private val viewState: CreatePostViewState = CreatePostViewState()


    fun updateTitle(newTitle: String) {
        Log.d(tag, "Update Title: ${viewState.title}")
        viewState.title.value = newTitle
        checkButtonActivity()
    }

    fun updateDescription(newDescription: String) {
        Log.d(tag, "Update Description: ${viewState.description}")
        viewState.description.value = newDescription
    }

    private fun checkButtonActivity() {
        Log.d(tag, "Check Button Activity")
        viewState.isButtonEnabled.value = viewState.title.value.isNotBlank()
    }


    fun createPost() =
        Post(title = viewState.title.value, description = viewState.description.value)
}