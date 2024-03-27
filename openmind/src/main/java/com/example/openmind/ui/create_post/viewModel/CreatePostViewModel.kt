package com.example.openmind.ui.create_post.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val tag = "CreatePostViewModel"

class CreatePostViewModel : ViewModel() {
    private val viewState: CreatePostViewState = CreatePostViewState()

    fun getDescription() = viewState.description.value
    fun getTitle() = viewState.title.value

    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    private fun checkButtonActivity() {
        viewState.isButtonEnabled.value = viewState.title.value.text.isNotBlank()
    }

    fun getButtonState() = viewState.isButtonEnabled.value

    fun createPost(): CreatePostDto {
        val newPost =
            CreatePostDto(
                title = viewState.title.value.text,
                description = viewState.description.value.text,
                category = viewState.activeCategory.getStringValue()
            )
        GlobalScope.launch {
            viewState.repository.postData(newPost)
        }
        return newPost
    }


    fun onTitleChange(): (TextFieldValue) -> Unit = { newTitle ->
        viewState.title.value = newTitle
    }

    fun onDescriptionChange(): (TextFieldValue) -> Unit = { newDescription ->
        viewState.description.value = newDescription
    }

    fun onCreatePostButton(): Unit {
        createPost()
    }
}