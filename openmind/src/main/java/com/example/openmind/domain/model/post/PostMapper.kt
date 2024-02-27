package com.example.openmind.domain.model.post

import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.repository.Repository

class PostMapper {
    val repository: Repository<Post> = PostRepositoryProvider.provideRepository()

    companion object {
        fun postToShortPost(post: Post): ShortPost {
            return ShortPost(
                postId = post.postId,
                title = post.title,
//                rating = post.rating,
                commentsCount = post.getCommentsCount(),
                createdDate = post.createdDate
            )
        }
    }


}