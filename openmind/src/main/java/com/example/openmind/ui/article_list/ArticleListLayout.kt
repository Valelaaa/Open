package com.example.openmind.ui.screen.ArticleList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.openmind.ui.screen.Screen


@Composable
fun ArticleListLayout(navController: NavController, modifier: Modifier = Modifier.fillMaxSize()) {

    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Article List Layout")
        Spacer(modifier = Modifier.height(10.dp))
        Button({
            navController.navigate(Screen.ArticleScreen.route)
        }) {
            Text(text = "To Article")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button({
            navController.navigate(Screen.CreateArticleScreen.route)
        })
        {
            Text(text = "Create new Article")
        }
    }
}