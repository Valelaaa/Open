package com.example.openmind.ui.components.post

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

const val TAG: String = "PostTopAppBar"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarPost(
    navController: NavController?,
    createPostViewModel: CreatePostViewModel,
) {
    val repository = PostRepositoryProvider.provideRepository()

    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = {
                Log.d(TAG, "Clicked Navigate Back")
                navController?.navigateUp()
            }) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back to post list",
                )
            }
        },
        actions = {
            Button(
                onClick = {
                    repository.addNewPost(createPostViewModel.createPost())
                    navController?.navigateUp()
                },
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                shape = RoundedCornerShape(18.dp),
                enabled = createPostViewModel.getButtonState(),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue40,
                    disabledContainerColor = Delimiter
                )
            ) {
                Text(
                    stringResource(R.string.create_button),
                    color = LightText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.ManropeSemiBoldW600
                )
            }
            IconButton(
                onClick = {
                    Log.d(TAG, "Clicked 'More' Button")

                },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "more",
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(90f)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun TopAppBarPostPreview() {
    OpenMindProjectTheme {
        Scaffold(topBar = {
            TopAppBarPost(null, createPostViewModel = viewModel())
        }, content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)
            ) {

            }
        })
    }
}
