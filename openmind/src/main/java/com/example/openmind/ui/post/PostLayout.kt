import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.CreatePostViewModel.CreatePostViewModel
import com.example.openmind.ui.components.post.TopAppBarPost


@Composable
fun PostLayout(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Post Layout")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostViewPreview() {
    val mockNavController = NavController(LocalContext.current)
    Scaffold(topBar = {
        TopAppBarPost(
            navController = mockNavController,
            CreatePostViewModel()
        )
    }) { paddingValues ->
        Box(
            Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(paddingValues))
    }
}