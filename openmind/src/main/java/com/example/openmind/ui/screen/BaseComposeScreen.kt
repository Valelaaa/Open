package com.example.openmind.ui.screen

import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseComposeScreen(
    navController: NavController,
    baseLayoutWithParams: @Composable (NavController, Modifier) -> Unit,
    toolbar: Toolbar?,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    OpenMindProjectTheme {
        Scaffold(modifier = modifier, topBar = {
            toolbar
        }, content = { padding ->
            baseLayoutWithParams(navController, modifier.padding(padding))
        })
    }
}


