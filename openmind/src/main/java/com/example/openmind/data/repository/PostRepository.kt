package com.example.openmind.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import com.example.openmind.data.post.Comment
import com.example.openmind.data.post.Post
import com.example.openmind.data.post.PostMapper
import com.example.openmind.data.post.ShortPost
import com.example.openmind.data.post.User
import com.example.openmind.data.repository.params.RequestParams
import com.example.openmind.data.viewModel.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import java.util.Date

class PostRepository : Repository<Post> {
    private var requestParams: RequestParams = RequestParams()
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
        mockPostList.sortBy { it.comments.size }
        mockPostList.reverse()
        mockShortPostList = mockPostList.map(PostMapper.Companion::postToShortPost).toMutableList()

    }


    override fun postData(data: Post): Boolean {
        val postId = data.postId
        var currentPost: Post?
        runBlocking {
            currentPost = data
            if (getData().first().firstOrNull { post: Post -> post.postId == postId } != null) {
                //request to update Post
                //updatePost(post)
                mockPostList.forEachIndexed { index, post: Post ->
                    if (post.postId == postId) {
                        mockPostList[index] = currentPost!!
                    }
                }
            } else {
                //request to create Post
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
        title = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
        author = "Jane Doe",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",

        rating = 302,
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
            getData().first().first { post: Post -> post.postId == postId }.rating += rating
        }
    }

    fun getMockPostList(activeSortType: MutableState<SortType>): MutableList<ShortPost> {
        when (activeSortType.value) {
            SortType.HOT -> {
                mockShortPostList.sortBy { it.rating }
                mockShortPostList.reverse()
            }

            SortType.FRESH -> mockShortPostList.sortBy {
                it.createdDate
            }

            SortType.OLD -> {
                mockShortPostList.sortBy { it.createdDate }
                mockShortPostList.reverse()
            }

            SortType.NEW -> mockShortPostList.sortBy {
                it.createdDate
            }
        }
        return mockShortPostList
    }

    fun addNewPost(post: Post): Boolean {
        mockShortPostList.add(PostMapper.postToShortPost(post))
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

}