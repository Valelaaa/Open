package com.example.openmind.ui.categories.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.BasicTopAppBar
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmindproject.ui.theme.NavigationIconStyle

@Composable
fun CategoriesAppBar(
    viewModel: CategoriesViewModel,
    navController: NavController,
    screen: Screen<*>,
) {
    BasicTopAppBar(
        viewModel = viewModel, navController = navController, currentScreen = screen,
        titleStyle = MaterialTheme.typography.titleLarge.merge(textAlign = TextAlign.Center),
        navIconStyle = NavigationIconStyle(modifier = Modifier.size(30.dp))
    )
}