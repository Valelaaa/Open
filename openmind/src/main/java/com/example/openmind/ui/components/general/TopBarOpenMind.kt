package com.example.openmind.ui.components.general

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.ui.components.post.TAG
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.ManropeBoldW700

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarOpenMind(navController: NavController, currentScreen: Screen) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                fontFamily = FontFamily.ManropeBoldW700,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Box(modifier = Modifier.padding(start = 30.dp)) {
                IconButton(
                    onClick = {
                        Log.d(TAG, "Clicked Navigate Back")
                        navController.navigateUp()
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "Back to post list",
                        tint = IconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        actions = {
            Box(modifier = Modifier.padding(end = 30.dp)) {

                IconButton(
                    onClick = {
                        Log.d(TAG, "Clicked 'Search' Button")
                    },
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_normal),
                        contentDescription = "search",
                        tint = IconColor,
                        modifier = Modifier.size(24.dp)

                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarOpenMindPreview() {
    val navController = NavController(LocalContext.current)

    Scaffold(
        topBar = {
            TopBarOpenMind(
                navController = navController,
                currentScreen = Screen.PostListScreen
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
        }

    }

}