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
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.postlist.PostListViewModel
import com.example.openmind.ui.components.general.TopBarOpenMind
import com.example.openmind.ui.post_list.PostListContentLayout
import com.example.openmind.ui.screen.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListLayout(
    screen: Screen,
    navController: NavController,
    category: Categories = Categories.BUG,
    modifier: Modifier = Modifier.fillMaxSize(),
    postListViewModel: PostListViewModel = viewModel(),
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBarOpenMind(navController = navController, currentScreen = screen)
    }, content = { padding ->
        PostListContentLayout(
            navController,
            viewModel = postListViewModel,
            currentCategory = category,
            modifier = Modifier.padding(padding)
        )
    })

}

@Preview
@Composable
fun PostListLayoutPreview() {
    val navController = NavController(LocalContext.current)
    Box {
        PostListLayout(
            screen = Screen.PostListScreen,
            navController = navController,
            category = Categories.FEATURE,
            postListViewModel = PostListViewModel()
        )
    }
}
