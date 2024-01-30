package com.example.openmind.domain.services

import com.example.openmind.data.topic.Topic
import com.example.openmind.data.viewModel.CurrentTopicViewModel

class TopicRequestMessage(topicViewModel: CurrentTopicViewModel) {
    val topicToPost: Topic

    init {
        topicToPost = topicViewModel.getTopic()

    }
}
