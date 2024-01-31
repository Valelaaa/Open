package com.example.openmind.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.openmind.ui.categories.CategoriesLayout
import com.example.openmind.ui.create_topic.CreateTopicLayout
import com.example.openmind.ui.screen.Article.TopicLayout
import com.example.openmind.ui.screen.ArticleList.TopicListLayout
import com.example.openmind.ui.screen.BaseComposeScreen
import com.example.openmind.ui.screen.Screen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.CategoriesScreen.route) {
        composable(Screen.CategoriesScreen.route) {
            BaseComposeScreen(
                navController = navController,
                content = { controller, modifier ->
                    CategoriesLayout(
                        navController = controller,
                        modifier
                    )
                },
                screen = Screen.CategoriesScreen
            )

        }
        composable(Screen.TopicListScreen.route) {
            BaseComposeScreen(
                navController = navController,
                content = { controller, modifier ->
                    TopicListLayout(
                        navController = controller,
                        modifier = modifier
                    )
                },
                screen = Screen.TopicListScreen

            )

        }
        composable(Screen.TopicScreen.route) {
            BaseComposeScreen(
                navController = navController,
                content = { controller, modifier ->
                    TopicLayout(
                        navController = controller,
                        modifier
                    )
                },
                screen = Screen.TopicScreen
            )
        }
        composable(Screen.CreateTopicScreen.route) {
            CreateTopicLayout(navController = navController, screen = Screen.CreateTopicScreen)
        }
    }
}

@Preview
@Composable
fun NavigatationPreview(){
    Navigation()
}