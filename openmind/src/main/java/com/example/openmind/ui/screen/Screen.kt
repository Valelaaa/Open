package com.example.openmind.ui.screen

sealed class Screen(val route: String = "", val title: String = "") {
    object CategoriesScreen : Screen(route = "categories_screen", title = "Categories")
    object TopicListScreen : Screen(route = "topic_list_screen", title = "Articles")
    object TopicScreen : Screen(route = "topic_screen", title = "Topic")
    object CreateTopicScreen : Screen(route = "create_topic_screen", title = "Create Topic")
}