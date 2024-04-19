package com.example.openmind.ui.create_post.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.enums.PostCategories

class CreatePostViewState {

    val title = mutableStateOf(TextFieldValue(""))
    val description = mutableStateOf(TextFieldValue(""))
    lateinit var activeCategory: PostCategories
    val categories = mutableStateListOf<PostCategories>()
    val isCategoriesLoading = mutableStateOf(true)

    val isCategoriesDropdownMenuVisible = mutableStateOf(false)
}
