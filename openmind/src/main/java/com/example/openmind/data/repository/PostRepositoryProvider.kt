package com.example.openmind.data.repository

import com.example.openmind.data.post.Post

object PostRepositoryProvider {
    private val repository = PostRepository()

    fun provideRepository(): Repository<Post> = repository
}