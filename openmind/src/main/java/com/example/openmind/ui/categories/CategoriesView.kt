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
fun CategoriesView(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Categories List Layout")
        Button({
            navController.navigate(Screen.PostListScreen.route)
        }, modifier = Modifier.padding(top = 10.dp)) {
            Text(text = "To Post List")
        }
        Button({
            navController.navigate(Screen.CreatePostScreen.route)
        }, modifier = Modifier.padding(top = 30.dp)) {
            Text(text = "create New Post")
        }
    }
}