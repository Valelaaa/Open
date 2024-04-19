package com.example.openmind.ui.navigation

import androidx.navigation.NavController
import com.example.openmind.enums.PostCategories
import com.example.openmind.ui.screen.Screen


fun NavController.navigateToPost(postId: String) =
    this.navigate(Screen.PostScreen.route + "/$postId")

fun NavController.navigateToPostList(category: PostCategories) =
    this.navigate(Screen.PostListScreen.route + "/${category}")

fun NavController.navigateToCreatePost(category: PostCategories) =
    this.navigate(Screen.CreatePostScreen.route + "/${category}")

fun NavController.navigateToSearchResult(query: String, category: PostCategories) =
    this.navigate(Screen.SearchResultsScreen.route + "/${category}/${query}")