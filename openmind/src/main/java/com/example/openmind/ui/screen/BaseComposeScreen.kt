package com.example.openmind.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseComposeScreen(
    navController: NavController,
    screen: Screen,
    modifier: Modifier = Modifier.fillMaxSize(),
    topBar: @Composable () -> Unit = {},
    content: @Composable (NavController, Modifier) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { /*TODO*/ }, actions = {
            IconButton(onClick = {
                navController.navigate(Screen.CreatePostScreen.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "CreatePost")
            }
        })
    }, content = { padding ->
        Box(modifier = Modifier.padding(padding)) {
            content(navController, modifier)
        }
    })
}


