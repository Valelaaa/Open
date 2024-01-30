package com.example.openmind.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.openmind.ui.screen.Screen

@Composable
fun CategoriesLayout(navController: NavController, modifier: Modifier = Modifier.fillMaxSize()) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Categories List Layout")
        Button({
            navController.navigate(Screen.TopicListScreen.route)
        }, modifier = Modifier.padding(top = 10.dp)) {
            Text(text = "To Article List")
        }
    }
}