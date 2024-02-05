package com.example.openmind.ui.post_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.PostListViewModel
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.postlist.post.PostShortView
import com.example.openmind.ui.components.postlist.selectSortingType.SortingSelector
import com.example.openmind.ui.theme.Delimiter

@Composable
fun PostListContentLayout(
    navController: NavController,
    viewModel: PostListViewModel,
    currentCategory: Categories,
    modifier: Modifier = Modifier
) {
    val postList = remember { viewModel.loadedPosts }
    val currentCategory = remember { currentCategory }

    Box(modifier = modifier) {
        LazyColumn(Modifier.borderBottom(1.dp, Delimiter)) {
            item {
                SortingSelector(
                    viewModel,
                    modifier = Modifier.padding(bottom = 28.dp, start = 28.dp)
                )
            }
            items(items = postList,
                key = { item -> item.postId },
                itemContent = { item ->
                    PostShortView(
                        navController = navController,
                        post = item,
                        category = currentCategory,
                    )
                })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostListPreview() {
    val navController = NavController(LocalContext.current)
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Title")})
        },
        content = { padding ->

            PostListContentLayout(
                navController = navController,
                viewModel = viewModel(),
                currentCategory = Categories.FEATURE,
                modifier = Modifier.padding(padding),
            )
        })
}