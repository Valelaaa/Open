package com.example.openmind.ui.screen

sealed class Screen(val route: String = "", val title: String = "") {
    object CategoriesScreen : Screen(route = "categories_screen", title = "Categories")
    object PostListScreen : Screen(route = "post_list_screen", title = "Posts")
    object PostScreen : Screen(route = "post_screen", title = "Post")
    object CreatePostScreen : Screen(route = "create_post_screen", title = "Create Post")
}