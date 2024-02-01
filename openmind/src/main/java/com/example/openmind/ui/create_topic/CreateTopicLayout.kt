package com.example.openmind.ui.create_topic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.example.openmind.ui.components.topic.TopAppBarTopic
import com.example.openmind.ui.components.topic.TopicBodyTextField
import com.example.openmind.ui.components.topic.TopicTitleTextField
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.BackgroundColor
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTopicLayout(
    navController: NavController?,
    modifier: Modifier = Modifier,
    screen: Screen,
    topicViewModel: CurrentTopicViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            TopAppBarTopic(navController = navController, "", topicViewModel)
        },
        content = { scaffoldPaddings ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor)
                    .padding(paddingValues = scaffoldPaddings)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    TopicTitleTextField(topicViewModel)
                }
                Box(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxSize()
                ) {
                    TopicBodyTextField(topicViewModel, modifier = Modifier.fillMaxSize())
                }
            }
        })
}

@Preview
@Composable
fun PreviewCreateArticleView() {
    OpenMindProjectTheme {
        CreateTopicLayout(navController = null, screen = Screen.CreateTopicScreen)
    }

}
