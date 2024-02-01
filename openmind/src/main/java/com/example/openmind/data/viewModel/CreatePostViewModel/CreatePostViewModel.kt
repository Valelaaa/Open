package com.example.openmind.data.viewModel.CreatePostViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.openmind.data.post.Post
const val tag = "CreatePostViewModel"
class CreatePostViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")

    //    val currentPost: MutableState<Post> = mutableStateOf(Post("",""))
    val isButtonEnabled = mutableStateOf(false)

    fun updateTitle(newTitle: String) {
        Log.d(tag,"Update Title: $title")
        title.value = newTitle
        checkButtonActivity()
    }

    fun updateDescription(newDescription: String) {
        Log.d(tag,"Update Description: $description")
        description.value = newDescription
    }

    private fun checkButtonActivity() {
        Log.d(tag,"Check Button Activity")
        isButtonEnabled.value = title.value.isNotBlank()
    }


    fun createPost() = Post(title = title.value, description = description.value)
}