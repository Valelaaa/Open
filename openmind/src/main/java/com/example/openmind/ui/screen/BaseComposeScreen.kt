package com.example.openmind.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
        topBar
    }, content = { padding ->
        Box(modifier = Modifier.padding(padding)) {
            content(navController, modifier)
        }
    })
}


