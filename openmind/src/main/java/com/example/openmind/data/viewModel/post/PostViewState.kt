package com.example.openmind.data.viewModel.post

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.post.Comment
import com.example.openmind.data.post.Post
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.SortType

class PostViewState {
    private var repository: PostRepository = PostRepositoryProvider.provideRepository()
    private var activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)
    private var post: MutableState<Post> = mutableStateOf(Post("", ""))
    private var comments = mutableStateListOf(*post.value.comments.toTypedArray())
    private val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )

    fun getComments(): MutableList<Comment> = comments
    fun setPost(post: Post) {
        this.post.value = post
        comments = mutableStateListOf(*post.comments.toTypedArray())
        repository.postData(post)
    }

    fun updatePostComments(comment: MutableList<Comment>) {
        this.comments = mutableStateListOf(*comment.toTypedArray())
        this.post.value.comments = comments
        repository.postData(this.post.value)
    }

    fun getSortingList() = sortingList
    fun setActiveSortType(sortType: SortType) {
        activeSortType.value = sortType
    }

    fun getActiveSortType(): MutableState<SortType> = mutableStateOf(activeSortType.value)
}