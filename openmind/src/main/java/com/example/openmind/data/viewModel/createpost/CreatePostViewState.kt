package com.example.openmind.data.viewModel.createpost

import androidx.compose.runtime.mutableStateOf

class CreatePostViewState {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val isButtonEnabled = mutableStateOf(false)
}
