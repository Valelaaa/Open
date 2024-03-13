package com.example.openmind.ui.post_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.ui.components.postlist.post.PostShortView
import com.example.openmind.ui.components.postlist.selectSortingType.SortingSelector
import com.example.openmind.ui.navigation.navigateToCreatePost
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.theme.LightGray80
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmind.utils.PostCategories

@Composable
fun PostListContentView(
    navController: NavController,
    viewModel: PostListViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SortingSelector(
                        viewModel,
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                    )
                    Button(
                        onClick = {
                            navController.navigateToCreatePost(category = viewModel.getPostCategory())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightGray80,
                            contentColor = SteelBlue60
                        ),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 15.dp),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 60.dp, minHeight = 30.dp)
                            .padding(top = 17.dp, bottom = 28.dp, start = 20.dp)
                            .align(Alignment.Top)
                    ) {
                        Text(
                            text = "Create post",
                            fontFamily = FontFamily.ManropeRegularW400,
                            fontSize = 14.sp,
                            color = SteelBlue60
                        )
                    }
                }
            }
            items(items = viewModel.getPostList(),
                itemContent = { item ->
                    PostShortView(
                        navController = navController,
                        post = item,
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
    val viewModel = PostListViewModel()
    viewModel.setCategory(PostCategories.BUG)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Title") })
        },
        content = { padding ->

            PostListContentView(
                navController = navController,
                viewModel = viewModel(),
                modifier = Modifier.padding(padding),
            )
        })
}