package com.example.openmind.ui.screen

sealed class Screen(val route: String) {
    object CategoriesScreen : Screen(route = "categories_screen")
    object ArticleListScreen : Screen("article_list_screen")
    object ArticleScreen : Screen("article_screen")
    object CreateArticleScreen : Screen("create_article_screen")
    object PostToScreen : Screen("post_to_screen")
}