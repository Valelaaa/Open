package com.example.openmind.ui.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.ManropeRegularW400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesAppBar(
    viewModel: CategoriesViewModel,
    navController: NavController,
    screen: Screen<*>,
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = screen.title,
                fontSize = 24.sp,
                fontFamily = FontFamily.ManropeRegularW400,
                lineHeight = 32.sp,
                letterSpacing = (-0.5).sp,
                textAlign = TextAlign.Center,
                color = DarkGray20
            )
        },
        navigationIcon = {
            Box(modifier = Modifier.padding(start = 30.dp)) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = "navigate up",
                    tint = IconColor,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable {
                            navController.navigateUp()
                        },
                )
            }
        },
    )
}