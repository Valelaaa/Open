package com.example.openmind.ui.create_post.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.GlobalViewModel

const val tag = "CreatePostViewModel"

class CreatePostViewModel : GlobalViewModel() {
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

    fun createPost(): Post {
        val newPost =
            Post(
                title = viewState.title.value.text,
                description = viewState.description.value.text,
                category = viewState.activeCategory
            )

        getRepositoryInstance().postData(newPost)
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