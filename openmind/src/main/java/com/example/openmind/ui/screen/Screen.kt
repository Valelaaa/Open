package com.example.openmind.ui.screen

sealed class Screen(val route: String = "", val title: String = "") {
    object CategoriesScreen : Screen(route = "categories_screen", title = "Categories")
    object ArticleListScreen : Screen(route = "article_list_screen", title = "Articles")
    object ArticleScreen : Screen(route = "article_screen", title = "Article")
    object CreateArticleScreen : Screen(route = "create_article_screen", title = "Create Article")
}