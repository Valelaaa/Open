package com.example.openmind.data.repository

import com.example.openmind.data.topic.Topic
import com.example.openmind.data.topic.User
import com.example.openmind.data.topic.UserComment
import java.time.LocalDateTime
import java.time.Month

class TopicRepositoryImpl : TopicRepository {
    private val mockTopicList: MutableList<Topic>

    init {
        mockTopicList = mutableListOf(
            Topic("How to manage your money better, daily? Any available lessons?", "description"),
            Topic(
                "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
                "description"
            ),
            Topic(
                "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
                "description"
            ),
            getMockTopic(),
            Topic("How to manage your money better, daily? Any available lessons?", "description"),
            Topic(
                "I would like to save money online like in my banking account, but to not touch them. Is it possible?",
                "description"
            ),
            Topic(
                "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
                "description"
            ),
            getMockTopic()
        )
    }

    override fun getMockTopic(): Topic = Topic(
        title = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
        author = "Jane Doe",
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
        createdDateTime = LocalDateTime.of(
            2024, Month.JANUARY, 20,
            21, 30
        ),
        description = "description",
    )

    override fun getMockTopicList(): List<Topic> = mockTopicList.toList()

    override fun addNewTopic(topic: Topic): Boolean = mockTopicList.add(topic)

}