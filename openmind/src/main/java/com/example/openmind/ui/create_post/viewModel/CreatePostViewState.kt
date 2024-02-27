package com.example.openmind.ui.create_post.viewModel

import androidx.compose.runtime.mutableStateOf

class CreatePostViewState {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val isButtonEnabled = mutableStateOf(false)
}
