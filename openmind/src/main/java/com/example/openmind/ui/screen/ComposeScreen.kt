package com.example.openmind.ui.screen

import NoRippleInteractionSource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import clickableWithoutRipple
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.navigation.navigateToPost
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.utils.SearchableViewModel

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
            if (viewModel is SearchableViewModel) {
                AnimatedVisibility(visible = viewModel.isSearchBarVisible()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .padding(paddingValues)
                            .clickableWithoutRipple(interactionSource = NoRippleInteractionSource.INSTANCE,
                                onClick = {
                                    viewModel.updateSearchBarVisibility(false)
                                    viewModel.resetSearch()
                                })
                    ) {
                        val searchResults by viewModel.searchResults.collectAsState()
                        LazyColumn(content = {
                            items(items = searchResults) { item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color.White)
                                        .borderBottom(1.dp, BorderLight)
                                        .defaultMinSize(minHeight = 60.dp)
                                        .padding(vertical = 10.dp, horizontal = 20.dp)
                                        .clickable(
                                            onClick = {
                                                navController.navigateToPost(item.postId)
                                            }
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.title,
                                        maxLines = 2,
                                        fontFamily = FontFamily.ManropeRegularW400,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        })
                    }
                }
            }
        }
    )
}
