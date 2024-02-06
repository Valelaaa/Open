package com.example.openmind.data.post

import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.PostRepositoryProvider

class PostMapper {
    val repository: PostRepository = PostRepositoryProvider.provideRepository()

    companion object {
        fun postToShortPost(post: Post): ShortPost {
            return ShortPost(
                postId = post.postId,
                title = post.title,
                rating = post.rating,
                commentsCount = post.getCommentsCount(),
                createdDate = post.createdDate
            )
        }
    }

    fun shortPostToPost(shortPost: ShortPost): Post {
        return repository.getPostById(shortPost.postId)
    }
}