package com.example.openmind.ui.create_post.viewModel

import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.domain.model.category.PostCategories

class CreatePostViewState {
    val title = mutableStateOf(TextFieldValue(""))
    val description = mutableStateOf(TextFieldValue(""))
    val isButtonEnabled = mutableStateOf(false)
    lateinit var activeCategory: PostCategories
}
