package com.example.openmind.ui.create_post.viewModel

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.utils.PostCategories

class CreatePostViewState {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val isButtonEnabled = mutableStateOf(false)
    lateinit var activeCategory:PostCategories
}
