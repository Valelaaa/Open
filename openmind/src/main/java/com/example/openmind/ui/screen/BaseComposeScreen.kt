package com.example.openmind.ui.screen

import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.openmind.ui.components.TopAppBarArticle
import com.example.openmind.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseComposeScreen(
    navController: NavController,
    screenTitle: String,
    content: @Composable (NavController, Modifier) -> Unit,
    toolbar: Toolbar?,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
    }, content = { padding ->
        Box(modifier = Modifier.padding(padding)) {
            content(navController, modifier)
        }
    })
}


