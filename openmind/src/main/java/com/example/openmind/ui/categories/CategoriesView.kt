package com.example.openmind.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.openmind.ui.navigation.navigateToPostList
import com.example.openmind.ui.screen.Screen
import com.example.openmind.utils.PostCategories

@Composable
fun CategoriesView(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Categories List Layout")
        Row(modifier = Modifier.padding(top = 10.dp, start = 30.dp, end = 30.dp)) {
            Button(
                {
                    navController.navigateToPostList(PostCategories.BUG)
                }, modifier = Modifier.weight(1f)
            ) {
                Text(text = "To Bug", maxLines = 1)
            }
            Button(
                {
                    navController.navigateToPostList(PostCategories.FEATURE)
                }, modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f)
            ) {
                Text(text = "To Feature", maxLines = 1)
            }
        }
        Button({
            navController.navigate(Screen.CreatePostScreen.route)
        }, modifier = Modifier.padding(top = 30.dp)) {
            Text(text = "create New Post")
        }
    }
}