package com.example.openmind.ui.navigation

import PostLayout
import PostListLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.openmind.ui.categories.CategoriesLayout
import com.example.openmind.ui.create_post.CreatePostLayout
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
        composable(Screen.PostListScreen.route) {
            PostListLayout(Screen.PostListScreen,navController = navController)

        }
        composable(Screen.PostScreen.route) {
            PostLayout(navController = navController, screen = Screen.PostScreen)
//            BaseComposeScreen(
//                navController = navController,
//                content = { controller, modifier ->
//                    PostLayout(
//                        navController = controller,
//                        modifier
//                    )
//                },
//                screen = Screen.PostScreen
//            )
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostLayout(navController = navController, screen = Screen.CreatePostScreen)
        }
    }
}

@Preview
@Composable
fun NavigatationPreview(){
    Navigation()
}