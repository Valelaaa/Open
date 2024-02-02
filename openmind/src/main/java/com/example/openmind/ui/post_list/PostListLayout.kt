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
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.Delimiter


@Composable
fun PostListLayout(
    navController: NavController,
    categories: Categories = Categories.BUG,
    modifier: Modifier = Modifier.fillMaxSize(),
    postListViewModel: PostListViewModel = viewModel<PostListViewModel>(),
) {
    val postList = remember { postListViewModel.loadedPosts }
    val currentCategory = remember { categories }

    Box(modifier = modifier) {
        LazyColumn(modifier.borderBottom(1.dp, Delimiter)) {
            item {
                SortingSelector(
                    postListViewModel,
                    modifier = Modifier.padding(bottom = 30.dp, start = 28.dp)
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
            TopAppBar(
                title = { Text("Posts") },
                navigationIcon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Button({
                                navController.navigate(Screen.PostScreen.route)
                            }) {
                                Text(text = "To Post")
                            }

                        }
                        Row {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        Row {
                            Button({
                                navController.navigate(Screen.CreatePostScreen.route)
                            })
                            {
                                Text(text = "Create new Post")
                            }
                        }
                    }

                },
            )
        },
        content = { padding ->
            PostListLayout(
                navController = navController,
                modifier = Modifier.padding(padding),
            )
        })
}