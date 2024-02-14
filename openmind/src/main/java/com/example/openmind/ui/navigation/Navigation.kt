package com.example.openmind.ui.navigation

import PostLayout
import PostListLayout
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.ui.categories.CategoriesLayout
import com.example.openmind.ui.create_post.CreatePostLayout
import com.example.openmind.ui.screen.BaseComposeScreen
import com.example.openmind.ui.screen.Screen

@RequiresApi(Build.VERSION_CODES.O)
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

        navigation(
            route = Screen.PostListScreen.route,
            startDestination = "${Screen.PostListScreen.route}/{category}"
        ) {
            composable(
                route = "${Screen.PostListScreen.route}/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
//                val category = backStackEntry.arguments?.getString("category")
                //TODO(Change to backStackEntry)
                val category = Categories.BUG.toString()


                if (category != null) {
                    PostListLayout(
                        screen = Screen.PostListScreen,
                        navController = navController,
                        category = Categories.valueOf(category)
                    )
                } else {
                    // Handle missing category argument
                    Log.d("Navigation", "missed Category Argument")
                }
            }
            composable(
                route = "${Screen.PostScreen.route}/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.StringType })
            ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getString("postId")
                if (postId != null) {
                    PostLayout(
                        navController = navController,
                        screen = Screen.PostScreen,
                        postId = postId
                    )
                } else {
                    // Handle missing postId argument
                    Log.d("Navigation", "missed PostId Argument")

                }
            }
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostLayout(navController = navController, screen = Screen.CreatePostScreen)
        }
    }
}

fun NavController.navigateToPost(postId: String) =
    this.navigate(Screen.PostScreen.route + "/$postId")