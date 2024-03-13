package com.example.openmind.ui.post.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.post.Post
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.user.User
import com.example.openmind.utils.SortType

class PostViewState {
    val currentUser = User("@janedoe")
    val commentToReply = mutableStateOf<Comment?>(null)
    val defaultCommentLines = 3
    val commentsBatchSize = 5
    val commentMessage = mutableStateOf(
        TextFieldValue("")
    )

    private var repository: PostRepository = PostRepositoryProvider.provideRepository()
    private var activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)
    var post: MutableState<Post> = mutableStateOf(Post("", ""))
    var comments = mutableStateOf(listOf(*post.value.comments.toTypedArray()))

    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )


    fun setPost(post: Post) {
        this.post.value = post
        comments.value = listOf(*post.comments.toTypedArray())
        repository.postData(post)
    }

    fun updatePostComments(comment: List<Comment>) {
        this.comments.value = listOf(*comment.toTypedArray())
        this.post.value.comments = comments.value
        repository.postData(this.post.value)
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType
    }

    fun getActiveSortType(): MutableState<SortType> = mutableStateOf(activeSortType.value)
}