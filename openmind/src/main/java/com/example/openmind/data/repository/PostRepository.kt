package com.example.openmind.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.post.Post
import com.example.openmind.domain.model.user.User
import com.example.openmind.domain.repository.Repository
import com.example.openmind.utils.PostCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import java.util.Date

class PostRepository : Repository<Post> {
    private var requestParams: RequestParams = RequestParams()
    private val mockPostList: MutableList<Post>

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
            Post(
                "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
                "description",
                category = PostCategories.FEATURE
            ),
            Post(
                "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
                "description",
                category = PostCategories.FEATURE
            ),
            getMockPost(),
        )
        mockPostList.sortBy { it.comments.size }
        mockPostList.reverse()

    }


    override fun postData(data: Post): Boolean {
        val postId = data.postId
        var currentPost: Post?
        runBlocking {
            currentPost = data
            if (getData().first().firstOrNull { post: Post -> post.postId == postId } != null) {
                //TODO(request to update Post)
                //updatePost(post)
                mockPostList.forEachIndexed { index, post: Post ->
                    if (post.postId == postId) {
                        mockPostList[index] = currentPost!!
                    }
                }
            } else {
                //TODO(request to create Post)
                //createPost(post)
                mockPostList.add(currentPost!!)
            }
        }
        return true
    }

    override fun getData(): Flow<List<Post>> {
        println("Send request with request params")
        return flowOf(mockPostList.toList())
    }

    override fun getById(id: String): Flow<Post> {
        var flowValue: Flow<Post>? = null
        runBlocking {
            flowValue = flowOf(getData().first().first { post -> post.postId == id })
        }
        return if (flowValue == null) flowOf(
            Post(
                "Empty post",
                "emptyPostDescription"
            )
        ) else flowValue!!
    }

    fun getMockPost(): Post = Post(
        title = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.".take(300),
        author = "Jane Doe",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.".take(300),

//        rating = 302,
        comments = mutableListOf(
            Comment(
                author = User("John Doe"),
                message = "Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur",
                subComments = mutableListOf(
                    Comment(
                        author = User("John Wick"), message = "@RichardMcClintock Hala Madrid!",
                    ),
                    Comment(
                        author = User("John Wick"),
                        message = "@JohnWick Mb u r wrong?"
                    ),
                    Comment(author = User("John Sick"), message = "I like ponies"),
                    Comment(
                        author = User("John Wick"), message = "@RichardMcClintock Hala Madrid!",
                    ),
                    Comment(
                        author = User("John Wick"),
                        message = "@JohnWick Mb u r wrong?"
                    ),
                    Comment(author = User("John Sick"), message = "I like ponies"),
                    Comment(
                        author = User("John Wick"), message = "@RichardMcClintock Hala Madrid!",
                    ),
                    Comment(
                        author = User("John Wick"),
                        message = "@JohnWick Mb u r wrong?"
                    ),
                    Comment(author = User("John Sick"), message = "I like ponies"),
                    Comment(
                        author = User("John Wick"), message = "@RichardMcClintock Hala Madrid!",
                    ),
                    Comment(
                        author = User("John Wick"),
                        message = "@JohnWick Mb u r wrong?"
                    ),
                    Comment(author = User("John Sick"), message = "I like ponies"),
                )

            ),
            Comment(author = User("John Snow"), message = "Winter is coming")
        ),
        createdDate = Date(
            2024 - 1900, 0, 20,
            21, 30
        ),
    )

    fun updateRating(postId: String, rating: Int) {
        //TODO("REQUEST TO UPDATE POST RATING")
        runBlocking {
            getData().first()
                .first { post: Post -> post.postId == postId }.ratingInfo.rating.value += rating
//            getData().first().first { post: Post -> post.postId == postId }.rating += rating
        }
    }


    fun addNewPost(post: Post): Boolean {
        return mockPostList.add(post)
    }

    fun getPostById(postId: String): Post = mockPostList.first { postId == it.postId }


    override fun setOrderParams(requestParams: RequestParams) {
        this.requestParams = requestParams
    }

    override fun getFetchParams(): RequestParams = requestParams

    override fun updateFetchParams(requestParams: RequestParams): Boolean {

        TODO("Not yet implemented")
    }

    fun findPostBySubstring(subString: String): List<Post> {
        if (subString.isBlank())
            return emptyList()
        val splitSubStrings = subString.trim().lowercase().split(" ")
        val found = mutableListOf<Post>()
        splitSubStrings.any { splitedSubString ->
            found.addAll(mockPostList.filter { post ->
                post.title.lowercase().contains(splitedSubString)
            })
        }
        return found.toList()
    }

}