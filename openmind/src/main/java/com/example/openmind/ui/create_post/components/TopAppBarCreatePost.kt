package com.example.openmind.ui.create_post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.ui.components.general.BasicTopAppBar
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.NightBlue
import com.example.openmindproject.ui.theme.NavigationIconStyle
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

const val TAG: String = "PostTopAppBar"

@Composable
fun TopAppBarCreatePost(
    navController: NavController,
    viewModel: CreatePostViewModel,
    screen: Screen<*>
) {

    BasicTopAppBar(
        viewModel = viewModel,
        navController = navController,
        currentScreen = screen,
        titleStyle = TextStyle(
            fontFamily = FontFamily.ManropeBoldW700,
            fontSize = 18.sp,
            lineHeight = 25.sp,
            textAlign = TextAlign.Center,
            color = NightBlue
        ),
        modifier = Modifier.padding(top = 20.dp),
        navIconStyle = NavigationIconStyle(
            iconColor = DarkBlue40
        )
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun TopAppBarPostPreview() {

    OpenMindProjectTheme {
        Scaffold(topBar = {
            TopAppBarCreatePost(
                rememberNavController(),
                CreatePostViewModel(),
                Screen.CreatePostScreen
            )
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
