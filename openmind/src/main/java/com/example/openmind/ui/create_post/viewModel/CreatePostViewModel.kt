package com.example.openmind.ui.create_post.viewModel

import android.util.Log
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.GlobalViewModel
import com.example.openmind.domain.model.category.PostCategories

const val tag = "CreatePostViewModel"

class CreatePostViewModel : GlobalViewModel() {
    private val viewState: CreatePostViewState = CreatePostViewState()

    fun getDescription() = viewState.description
    fun getTitle() = viewState.title
    fun updateTitle(newTitle: String) {
        Log.d(tag, "Update Title: ${viewState.title}")
        viewState.title.value = newTitle
        checkButtonActivity()
    }

    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    fun updateDescription(newDescription: String) {
        Log.d(tag, "Update Description: ${viewState.description}")
        viewState.description.value = newDescription
    }

    private fun checkButtonActivity() {
        Log.d(tag, "Check Button Activity")
        viewState.isButtonEnabled.value = viewState.title.value.isNotBlank()
    }

    fun getButtonState() = viewState.isButtonEnabled.value

    fun createPost(): Post {
        val newPost =
            Post(
                title = viewState.title.value,
                description = viewState.description.value,
                category = viewState.activeCategory
            )
        getRepositoryInstance().addNewPost(newPost)
        return newPost
    }
}