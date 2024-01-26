package com.example.openmind.ui.components.topiclist.topic

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openmind.R
import com.example.openmind.data.topic.Topic
import com.example.openmind.data.viewModel.TopicListViewModel

@Composable
fun TopicShortComposeView(topic: Topic, topicListViewModel: TopicListViewModel = viewModel()) {
    Image(painter = painterResource(R.drawable.user_pic), contentDescription = "")
}

@Preview
@Composable
fun TopicShortComposeViewPreview() {
    val topic = Topic(
        "Can we expedite transaction confirma-tions? Cuz yesterday I just tried some and no result...",
        description = "description"
    )
    TopicShortComposeView(topic = topic, viewModel())
}