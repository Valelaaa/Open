package com.example.openmind.ui.create_post

import NoRippleInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import clickableWithoutRipple
import com.example.openmind.ui.components.post.CreateTopAppBarPost
import com.example.openmind.ui.components.post.PostBodyTextField
import com.example.openmind.ui.components.post.PostTitleTextField
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.theme.BackgroundColor

@Composable
fun CreatePostContentView(viewModel: CreatePostViewModel, modifier: Modifier = Modifier) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            PostTitleTextField(viewModel)
        }
        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxSize()
                .weight(1f)
                .clickableWithoutRipple(interactionSource = NoRippleInteractionSource.INSTANCE,
                    onClick =
                    { focusRequester.requestFocus() }
                )
        ) {
            PostBodyTextField(
                viewModel = viewModel,
                modifier = Modifier
                    .focusRequester(focusRequester = focusRequester)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CreatePostContentPreview() {
    val navController: NavController = rememberNavController()
    val viewModifier = CreatePostViewModel()
    Scaffold(
        topBar = {
            CreateTopAppBarPost(
                navController = navController,
                createPostViewModel = viewModifier
            )
        }
    ) { paddingValues ->
        CreatePostContentView(
            viewModel = viewModifier,
            modifier = Modifier.padding(paddingValues)
        )
    }
}