package com.example.openmind.ui.categories.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.TopAppBarOpenMind
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeRegularW400

@Composable
fun CategoriesAppBar(
    viewModel: CategoriesViewModel,
    navController: NavController,
    screen: Screen<*>,
) {
    TopAppBarOpenMind(
        viewModel = viewModel, navController = navController, currentScreen = screen,
        titleStyle = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily.ManropeRegularW400,
            lineHeight = 32.sp,
            letterSpacing = (-0.5).sp,
            textAlign = TextAlign.Center,
            color = DarkGray20
        ),
    )
}