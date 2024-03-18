package com.example.openmind.ui.components.general

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.ui.SearchableViewModel
import com.example.openmind.ui.create_post.components.TAG
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmindproject.ui.theme.NavigationIconStyle

@Composable
@OptIn(
    ExperimentalMaterial3Api::class
)
fun BasicTopAppBar(
    viewModel: ViewModel,
    navController: NavController,
    currentScreen: Screen<*>,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = TextStyle(
        fontFamily = FontFamily.ManropeBoldW700,
        fontSize = 16.sp,
        lineHeight = 25.sp,
        textAlign = TextAlign.Center
    ),
    navIconStyle: NavigationIconStyle = NavigationIconStyle.defaultIconStyle()
) {
    val focusManager = LocalFocusManager.current

    Box(modifier.background(lightColorScheme().background)) {
        CenterAlignedTopAppBar(
            title = {
                Box {
                    Text(
                        text = currentScreen.title,
                        style = titleStyle
                    )

                }
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
                            painterResource(id = R.drawable.navigation_up),
                            contentDescription = "Back to post list",
                            tint = navIconStyle.iconColor,
                            modifier = navIconStyle.modifier
                        )
                    }
                }
            },
            actions = {
                Box(modifier = Modifier.padding(end = 30.dp)) {
                    if (viewModel is SearchableViewModel)
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
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable(onClick = {
                                        viewModel.updateSearchBarVisibility(isVisible = !viewModel.isSearchBarVisible())
                                    })
                            )
                        }
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = lightColorScheme().background
            )
        )
        if (viewModel is SearchableViewModel)
            AnimatedVisibility(
                visible = viewModel.isSearchBarVisible(),
                enter = expandHorizontally(
                    expandFrom = Alignment.CenterHorizontally,
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = shrinkHorizontally(
                    shrinkTowards = Alignment.CenterHorizontally,
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    SearchBar(viewModel = viewModel,
                        navController = navController,
                        onSearch = {
                            focusManager.clearFocus()
                        })
                }
            }
    }
}
