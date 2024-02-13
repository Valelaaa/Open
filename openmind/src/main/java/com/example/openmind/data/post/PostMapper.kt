package com.example.openmind.data.post

import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.repository.Repository

class PostMapper {
    val repository: Repository<Post> = PostRepositoryProvider.provideRepository()

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


}