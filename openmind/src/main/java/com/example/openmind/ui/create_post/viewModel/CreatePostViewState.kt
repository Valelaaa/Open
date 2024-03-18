package com.example.openmind.ui.create_post.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.category.PostCategories

class CreatePostViewState {
    val repository = PostRepositoryProvider.provideRepository()

    val title = mutableStateOf(TextFieldValue(""))
    val description = mutableStateOf(TextFieldValue(""))
    val isButtonEnabled = mutableStateOf(false)
    lateinit var activeCategory: PostCategories
}
