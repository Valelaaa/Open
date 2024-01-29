package com.example.openmind.ui.navigation

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.openmind.ui.screen.BaseComposeScreen
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.categories.CategoriesLayout
import com.example.openmind.ui.create_topic.CreateTopicLayout
import com.example.openmind.ui.screen.Article.TopicLayout
import com.example.openmind.ui.screen.ArticleList.TopicListLayout

@Composable
fun Navigation(toolbar: Toolbar? = null, contentView: View? = null) {
    val navController = rememberNavController()
    val globalModifier: Modifier = Modifier.fillMaxSize()
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
                toolbar = null,
                modifier = globalModifier,
                screenTitle = Screen.CategoriesScreen.title
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
                toolbar = null,
                modifier = globalModifier,
                screenTitle = Screen.TopicListScreen.title

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
                toolbar = null,
                modifier = globalModifier,
                screenTitle = Screen.TopicScreen.title
            )
        }
        composable(Screen.CreateTopicScreen.route) {
            CreateTopicLayout(navController = navController, screen = Screen.CreateTopicScreen)
        }
    }
}





