package com.example.openmind.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import clickableWithoutRipple
import com.example.openmind.ui.screen.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <V : ViewModel> ComposeScreen(
    screen: Screen<V>,
    navController: NavController,
    args: Map<String, String> = emptyMap()
) {
    val viewModel = viewModel(screen.viewModelClass)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { screen.topAppBar(viewModel, navController) },
        content = { paddingValues ->

            screen.content(
                viewModel, navController,
                args, Modifier.padding(paddingValues)
            )
            if (viewModel is Searchable) {
                AnimatedVisibility(visible = viewModel.isSearchBarVisible()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickableWithoutRipple(interactionSource = NoRippleInteractionSource.INSTANCE,
                                onClick = {
                                    viewModel.updateSearchBarVisibility(false)
                                })
                    )
                }
            }
        }
    )
}