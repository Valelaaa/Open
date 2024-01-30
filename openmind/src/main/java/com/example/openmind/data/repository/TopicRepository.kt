package com.example.openmind.data.repository

import com.example.openmind.data.topic.Topic

interface TopicRepository {
    fun getMockTopic(): Topic
    fun getMockTopicList(): List<Topic>
}