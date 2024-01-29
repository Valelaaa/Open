package com.example.openmind.domain.services

import com.example.openmind.data.topic.Topic
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.google.gson.annotations.SerializedName

class TopicRequestMessage(topicViewModel: CurrentTopicViewModel) {
    @SerializedName("topic")
    val topicToPost: Topic

    init {
        topicToPost = topicViewModel.getTopic()

    }
}
