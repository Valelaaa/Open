package com.example.openmind.ui.screen.Article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.example.openmind.ui.components.topic.TopAppBarTopic


@Composable
fun TopicLayout(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Article Layout")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopicViewPreview() {
    val mockNavController = NavController(LocalContext.current)
    Scaffold(topBar = {
        TopAppBarTopic(
            navController = mockNavController, "",
            CurrentTopicViewModel()
        )
    }) { paddingValues ->
        TopicLayout(navController = mockNavController, modifier = Modifier.padding(paddingValues))
    }
}