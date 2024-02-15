import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.post.Post
import com.example.openmind.data.viewModel.post.PostViewModel
import com.example.openmind.data.viewModel.postlist.PostListViewModel
import com.example.openmind.ui.components.general.TopBarOpenMind
import com.example.openmind.ui.post.PostContentView
import com.example.openmind.ui.screen.Screen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostView(
    postId: String,
    navController: NavController,
    screen: Screen,
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = viewModel(),
) {
    Scaffold(topBar = {
        TopBarOpenMind(navController = navController, currentScreen = screen)
    }) { scaffoldPaddings ->
        PostContentView(
            navController = navController,
            postId = postId,
            modifier = Modifier.padding(scaffoldPaddings),
            viewModel = viewModel
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PostViewPreview() {
    val navController = NavController(LocalContext.current)
    val viewModel = PostViewModel()
    val postViewModel = PostListViewModel()
    val post = postViewModel.getPostList().first { post: Post -> post.getCommentsCount() != 0 }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PostView(
            postId = post.postId,
            navController = navController,
            screen = Screen.PostScreen
        )
    }
}