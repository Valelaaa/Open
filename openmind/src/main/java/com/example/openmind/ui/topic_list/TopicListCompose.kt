package com.example.openmind.ui.screen.ArticleList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.TopicListViewModel
import com.example.openmind.ui.components.topiclist.selectSortingType.SortByComponent
import com.example.openmind.ui.components.topiclist.topic.TopicShortComposeView
import com.example.openmind.ui.screen.Screen


@Composable
fun TopicListLayout(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    topicListViewModel: TopicListViewModel = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            SortByComponent(topicListViewModel)
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(items = topicListViewModel.loadedTopics, itemContent = { item ->
                    TopicShortComposeView(item,topicListViewModel)
                })
            }

        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopicListPreview() {
    val navController = NavController(LocalContext.current)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Topics") },
                navigationIcon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Button({
                                navController.navigate(Screen.TopicScreen.route)
                            }) {
                                Text(text = "To Topic")
                            }

                        }
                        Row {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        Row {
                            Button({
                                navController.navigate(Screen.CreateTopicScreen.route)
                            })
                            {
                                Text(text = "Create new Topic")
                            }
                        }
                    }

                },
            )
        },
        content = { padding ->
            TopicListLayout(
                navController = navController,
                Modifier.padding(padding),
            )
        })
}