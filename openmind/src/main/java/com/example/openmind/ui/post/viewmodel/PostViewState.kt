package com.example.openmind.ui.post.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.data.repository.CommentsRepository
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.CommentsRepositoryProvider
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.comment.dto.CommentDto
import com.example.openmind.domain.model.post.CreatePostDto
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.domain.model.user.User
import com.example.openmind.utils.SortType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostViewState {
    val currentUser = User("@janedoe")
    val postIsLoading = mutableStateOf(true)
    val commentsLoading = mutableStateOf(true)

    val commentToReply = mutableStateOf<CommentDto?>(null)
    val defaultCommentLines = 3
    val commentsBatchSize = 5
    val commentMessage = mutableStateOf(
        TextFieldValue("")
    )
    private val postRepository: PostRepository = PostRepositoryProvider.provideRepository()
    private val commentRepository: CommentsRepository =
        CommentsRepositoryProvider.provideRepository()

    private var activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )
    var currentPostId: String = ""
        get() = field
        set(value) {
            field = value
        }

    var post: MutableState<PostDto> = mutableStateOf(PostDto("", ""))


    var comments = mutableStateListOf<CommentDto>()

    fun fetchPost() {
        GlobalScope.launch {
            postIsLoading.value = true
            postRepository.fetchPostById(currentPostId ?: "").collect {
                post.value = it
                postIsLoading.value = false
            }
        }

    }

    fun fetchComments() {
        GlobalScope.launch {
            commentsLoading.value = true
            comments.clear()
            commentRepository.fetchCommentsByPostId(currentPostId).collect {
                comments.addAll(it)
                commentsLoading.value = false
            }
        }
    }

    fun updatePostComments(comment: List<CommentDto>) {
        this.comments = listOf(*comment.toTypedArray()).toMutableStateList()
        var postToUpdate = CreatePostDto(
            postId = post.value.postId,
            title = post.value.title,
            description = post.value.description,
            category = post.value.category
        )
        GlobalScope.launch {
            postRepository.postData(postToUpdate)
        }
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType
    }

    fun getActiveSortType(): MutableState<SortType> = mutableStateOf(activeSortType.value)
}