package com.example.openmind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.screen.ComposeScreen
import com.example.openmind.ui.screen.Screen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.ProfileScreen.route) {
        composable(Screen.CategoriesScreen.route) {
            ComposeScreen(screen = Screen.CategoriesScreen, navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ComposeScreen(screen = Screen.ProfileScreen, navController = navController)
        }
        navigation(
            route = Screen.PostListScreen.route,
            startDestination = "${Screen.PostListScreen.route}/{category}",
        ) {
            composable(
                route = "${Screen.PostListScreen.route}/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category")
                ComposeScreen(
                    screen = Screen.PostListScreen,
                    navController = navController,
                    args = mapOf("category" to (category ?: PostCategories.BUG.getStringValue()))
                )
            }

            composable(
                route = "${Screen.CreatePostScreen.route}/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category")
                ComposeScreen(
                    screen = Screen.CreatePostScreen, navController = navController,
                    args = mapOf("category" to (category ?: PostCategories.BUG.getStringValue()))
                )
            }
            composable(
                route = "${Screen.PostScreen.route}/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.StringType })
            ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getString("postId")
                ComposeScreen(
                    screen = Screen.PostScreen, navController = navController,
                    args = mapOf("postId" to (postId ?: ""))
                )
            }
            composable(
                route = "${Screen.SearchResultsScreen.route}/{category}/{searchQuery}",
                arguments = listOf(
                    navArgument("category") { type = NavType.StringType },
                    navArgument("searchQuery") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery")
                val category = backStackEntry.arguments?.getString("category")
                ComposeScreen(
                    screen = Screen.SearchResultsScreen,
                    navController = navController,
                    args = mapOf(
                        "category" to (category ?: (PostCategories.BUG.getStringValue())),
                        "searchQuery" to (searchQuery ?: "")
                    )
                )
            }
        }
        composable(route = "${Screen.SuccessRegisteredPostScreen.route}") {
            ComposeScreen(
                screen = Screen.SuccessRegisteredPostScreen,
                navController = navController
            )
        }
    }
}

fun NavController.navigateToPost(postId: String) =
    this.navigate(Screen.PostScreen.route + "/$postId")

fun NavController.navigateToPostList(category: PostCategories) =
    this.navigate(Screen.PostListScreen.route + "/${category}")

fun NavController.navigateToCreatePost(category: PostCategories) =
    this.navigate(Screen.CreatePostScreen.route + "/${category}")

fun NavController.navigateToSearchResult(query: String, category: PostCategories) =
    this.navigate(Screen.SearchResultsScreen.route + "/${category}/${query}")