package com.example.openmind.domain.services

import com.example.openmind.data.post.Post
import com.example.openmind.data.viewModel.createpost.CreatePostViewModel

class PostRequestMessage(postViewModel: CreatePostViewModel) {
    val post: Post

    init {
        post = postViewModel.createPost()

    }
}
