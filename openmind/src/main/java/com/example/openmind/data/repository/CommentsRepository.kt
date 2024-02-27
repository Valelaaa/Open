package com.example.openmind.data.repository

import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.user.User
import com.example.openmind.domain.api.params.RequestParams
import com.example.openmind.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CommentsRepository : Repository<Comment> {
    override fun getData(): Flow<List<Comment>> =
        flowOf(
            listOf(
                Comment(
                    author = User("John Doe"),
                    message = "Sounds good",
                    subComments = mutableListOf(
                        Comment(
                            author = User("John Wick"), message = "You wrong",
                        ),
                        Comment(
                            author = User("John Wick"),
                            message = "@JohnWick Mb u r wrong?"
                        ),
                        Comment(author = User("John Sick"), message = "I like ponies")
                    )

                ),
                Comment(
                    author = User("John Doe"),
                    message = "Sounds good",
                    subComments = mutableListOf(
                        Comment(
                            author = User("John Wick"), message = "You wrong",
                        ),

                        Comment(
                            author = User("John Wick"),
                            message = "Mb u r wrong?"
                        ),
                        Comment(author = User("John Sick"), message = "I like ponies")
                    )

                ),
                Comment(author = User("John Snow"), message = "Winter is coming")
            ),
        )


    override fun getById(id: String): Flow<Comment> {
        TODO("Not yet implemented")
    }

    override fun setOrderParams(requestParams: RequestParams) {
        TODO("Not yet implemented")
    }

    override fun getFetchParams(): RequestParams {
        TODO("Not yet implemented")
    }

    override fun updateFetchParams(requestParams: RequestParams): Boolean {
        TODO("Not yet implemented")
    }

    override fun postData(data: Comment): Boolean {
        TODO("Not yet implemented")
    }

}