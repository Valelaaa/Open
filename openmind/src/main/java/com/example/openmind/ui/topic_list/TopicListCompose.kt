package com.example.openmind.ui.screen.ArticleList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.TopicListViewModel
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.topiclist.selectSortingType.SortingSelector
import com.example.openmind.ui.components.topiclist.topic.TopicShortView
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.Delimiter


@Composable
fun TopicListLayout(
    navController: NavController,
    categories: Categories = Categories.BUG,
    modifier: Modifier = Modifier.fillMaxSize(),
    topicListViewModel: TopicListViewModel = viewModel(),
) {

    val topicList = remember { mutableStateOf(topicListViewModel.loadedTopics) }
    //For better optimization we should use these objects for all views
    val currentCategory = remember { categories }

    Box(modifier = modifier) {
        LazyColumn(modifier.borderBottom(1.dp, Delimiter)) {
            item {
                SortingSelector(
                    topicListViewModel,
                    modifier = Modifier.padding(bottom = 30.dp, start = 28.dp)
                )
            }
            items(items = topicList.value,
                key = { item -> item.topicId },
                itemContent = { item ->
                    TopicShortView(
                        navController = navController,
                        topic = item,
                        category = currentCategory,
                    )
                })
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
                modifier = Modifier.padding(padding),
            )
        })
}