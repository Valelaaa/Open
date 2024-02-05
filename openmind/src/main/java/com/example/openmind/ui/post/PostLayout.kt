import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.postviewmodel.PostViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel;
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.ui.components.general.TopBarOpenMind
import com.example.openmind.ui.post.PostContentLayout
import com.example.openmind.ui.screen.Screen


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostLayout(
    navController: NavController,
    modifier: Modifier = Modifier,
    screen: Screen,
    viewModel: PostViewModel = viewModel()
) {
    val post = PostRepositoryProvider.provideRepository().getMockPost()

    Scaffold(topBar = {
        TopBarOpenMind(navController = navController, currentScreen = screen)
    }) { scaffoldPaddings ->
        PostContentLayout(
            navController = navController,
            currentPost = post,
            modifier = Modifier.padding(scaffoldPaddings)
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostViewPreview() {
    val mockNavController = NavController(LocalContext.current)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PostLayout(navController = mockNavController, screen = Screen.PostScreen)
    }
}