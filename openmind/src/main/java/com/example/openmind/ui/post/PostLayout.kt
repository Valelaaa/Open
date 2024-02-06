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
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.post.PostViewModel
import com.example.openmind.ui.components.general.TopBarOpenMind
import com.example.openmind.ui.post.PostContentLayout
import com.example.openmind.ui.screen.Screen


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostLayout(
    postId: String,
    navController: NavController,
    screen: Screen,
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = viewModel(),
) {
    Scaffold(topBar = {
        TopBarOpenMind(navController = navController, currentScreen = screen)
    }) { scaffoldPaddings ->
        PostContentLayout(
            navController = navController,
            postId = postId,
            modifier = Modifier.padding(scaffoldPaddings)
        )
    }

}

@Preview
@Composable
fun PostViewPreview() {
    val mockNavController = NavController(LocalContext.current)
    val mockPost = PostRepositoryProvider.provideRepository().getMockPostList().first()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PostLayout(
            postId = mockPost.postId,
            navController = mockNavController,
            screen = Screen.PostScreen
        )
    }
}