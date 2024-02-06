package com.example.openmind.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import com.example.openmind.data.post.Post
import com.example.openmind.data.post.PostMapper
import com.example.openmind.data.post.ShortPost
import com.example.openmind.data.post.User
import com.example.openmind.data.post.UserComment
import com.example.openmind.data.viewModel.SortBy
import java.util.Date

class PostRepositoryImpl : PostRepository {
    private val mockPostList: MutableList<Post>
    private val mockShortPostList: MutableList<ShortPost>

    init {
        mockPostList = mutableStateListOf(
            Post(
                "How to manage your money better, daily? Any available lessons?",
                "description"
            ),
            Post(
                "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
                "description"
            ),
            Post(
                "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
                "description"
            ),
            getMockPost(),
            Post(
                "How to manage your money better, daily? Any available lessons?",
                "description"
            ),
            Post(
                "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
                "description"
            ),
            Post(
                "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
                "description"
            ),
            getMockPost(),
        )

        mockPostList.sortBy { it.createdDate }
        mockPostList.reverse()
        mockShortPostList =
            mockPostList.map(PostMapper.Companion::postToShortPost).toMutableList()
    }

    override fun getMockPost(): Post = Post(
        title = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
        author = "Jane Doe",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",

        rating = 302,
        comments = listOf(
            UserComment(
                author = User("John Doe"),
                message = "Sounds good",
                subComments = listOf(
                    UserComment(
                        author = User("John Wick"), message = "You wrong",
                        subComments = listOf(
                            UserComment(
                                author = User("John Wick"),
                                message = "Mb u r wrong?"
                            )
                        )
                    ),

                    UserComment(author = User("John Sick"), message = "I like ponies")
                )

            ),
            UserComment(author = User("John Snow"), message = "Winter is coming")
        ),
        createdDate = Date(
            2024 - 1900, 0, 20,
            21, 30
        ),
    )


    override fun getMockPostList(activeSortType: MutableState<SortBy>): MutableList<ShortPost> {
        when (activeSortType.value) {
            SortBy.HOT -> {
                mockShortPostList.sortBy { it.rating }
                mockShortPostList.reverse()
            }

            SortBy.FRESH -> mockShortPostList.sortBy {
                it.createdDate
            }

            SortBy.OLD -> {
                mockShortPostList.sortBy { it.createdDate }
                mockShortPostList.reverse()
            }
        }
        return mockShortPostList
    }

    override fun addNewPost(post: Post): Boolean {
        mockShortPostList.add(PostMapper.postToShortPost(post))
        return mockPostList.add(post)
    }

    override fun getPostById(postId: String): Post =
        mockPostList.first { postId == it.postId }

}