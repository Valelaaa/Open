package com.example.openmind.ui.post.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.user.User
import com.example.openmind.utils.SortType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostViewState {
    val currentUser = User("@janedoe")
    val commentToReply = mutableStateOf<Comment?>(null)
    val defaultCommentLines = 3
    val commentsBatchSize = 5
    val commentMessage = mutableStateOf(
        TextFieldValue("")
    )
    var repository: PostRepository = PostRepositoryProvider.provideRepository()
    private var activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )
    private var _currentPost: String? = null
    var currentPostId: String?
        get() = _currentPost
        set(value) {
            if (value != null) {
                fetchPost(value)
            }
        }
    var post: MutableState<Post> = mutableStateOf(Post("", ""))


    var comments = mutableStateOf(emptyList<Comment>())

    private fun fetchPost(postId: String) {
        GlobalScope.launch {
            repository.fetchById(postId).collect {
                post.value = it
                comments.value = listOf(*it.comments.toTypedArray())
            }
        }

    }


    fun updatePostComments(comment: List<Comment>) {
        this.comments.value = listOf(*comment.toTypedArray())
        this.post.value.comments = comments.value
        GlobalScope.launch {
            repository.postData(post.value)
        }
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType
    }

    fun getActiveSortType(): MutableState<SortType> = mutableStateOf(activeSortType.value)
}