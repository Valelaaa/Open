package com.example.openmind.data.repository

import com.example.openmind.data.topic.Topic
import com.example.openmind.data.topic.User
import com.example.openmind.data.topic.UserComment
import java.time.LocalDateTime
import java.time.Month

class TopicRepositoryImpl : TopicRepository {
    override fun getMockTopic(): Topic = Topic(
        title = "Can we expedite transaction confirmations? Cuz yesterday I just tried some and no result...",
        author = "Jane Doe",
        rating = 0,
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
        createdDateTime = LocalDateTime.of(
            2024, Month.JANUARY, 20,
            21, 30
        ),
        description = "description",
    )
}