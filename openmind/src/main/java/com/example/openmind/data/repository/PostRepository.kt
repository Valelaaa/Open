package com.example.openmind.data.repository

import com.example.openmind.data.post.Post

interface PostRepository {
    fun getMockPost(): Post
    fun getMockPostList(): MutableList<Post>
    fun addNewPost(post: Post): Boolean
}