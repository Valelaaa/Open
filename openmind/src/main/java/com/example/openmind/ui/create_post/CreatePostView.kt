package com.example.openmind.ui.create_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.data.viewModel.createpost.CreatePostViewModel
import com.example.openmind.ui.components.post.PostBodyTextField
import com.example.openmind.ui.components.post.PostTitleTextField
import com.example.openmind.ui.components.post.TopAppBarPost
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.BackgroundColor
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostView(
    navController: NavController?,
    modifier: Modifier = Modifier,
    screen: Screen,
    createPostViewModel: CreatePostViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBarPost(navController = navController, createPostViewModel)
        },
        content = { scaffoldPaddings ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor)
                    .padding(paddingValues = scaffoldPaddings)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    PostTitleTextField(createPostViewModel)
                }
                Box(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxSize()
                ) {
                    PostBodyTextField(createPostViewModel, modifier = Modifier.fillMaxSize())
                }
            }
        })
}

@Preview
@Composable
fun PreviewCreatePostView() {
    OpenMindProjectTheme {
        CreatePostView(navController = null, screen = Screen.CreatePostScreen)
    }

}
