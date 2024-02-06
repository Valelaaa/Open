package com.example.openmind.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.post.Post
import com.example.openmind.data.post.ShortPost
import com.example.openmind.data.viewModel.SortBy

interface PostRepository {
    fun getMockPost(): Post
    fun getMockPostList(activeSortType: MutableState<SortBy> = mutableStateOf(SortBy.HOT)): MutableList<ShortPost>
    fun addNewPost(post: Post): Boolean
    fun getPostById(postId: String): Post

}