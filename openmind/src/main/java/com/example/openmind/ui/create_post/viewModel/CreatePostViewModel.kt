package com.example.openmind.ui.create_post.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.di.repository.CategoriesRepositoryProvider
import com.example.openmind.di.repository.PostRepositoryProvider
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.ui.GlobalViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

const val tag = "CreatePostViewModel"

class CreatePostViewModel : GlobalViewModel() {
    private val viewState: CreatePostViewState = CreatePostViewState()
    val repository = PostRepositoryProvider.provideRepository()
    val categoryRepository = CategoriesRepositoryProvider.provideRepository()

    fun getDropdownVisibility() = viewState.isCategoriesDropdownMenuVisible.value
    fun setDropdownVisibility(isVisible: Boolean) {
        viewState.isCategoriesDropdownMenuVisible.value = isVisible
    }

    fun getDescription() = viewState.description.value
    fun getTitle() = viewState.title.value

    fun getCategory(): String = viewState.activeCategory.getStringValue()
    fun setCategory(postCategories: PostCategories) {
        viewState.activeCategory = postCategories
    }

    fun createPost(): CreatePostDto {
        val newPost =
            CreatePostDto(
                title = viewState.title.value.text,
                description = viewState.description.value.text,
                category = viewState.activeCategory.getStringValue()
            )
        GlobalScope.launch {
            repository.postData(newPost)
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

    fun fetchCategories() {
        GlobalScope.launch {
            viewState.isCategoriesLoading.value = true
            categoryRepository.fetchAll().catch { cause: Throwable -> handleError(cause) }
                .collect { list ->
                    viewState.categories.clear()
                    viewState.categories.addAll(list.map { PostCategories.valueOf(it.categoryName) })
                    viewState.isCategoriesLoading.value = false
                }
        }
    }

    fun getCategoriesList() = viewState.categories
}