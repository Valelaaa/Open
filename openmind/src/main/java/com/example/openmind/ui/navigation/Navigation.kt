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
import com.example.openmind.ui.create_article.CreateArticleLayout
import com.example.openmind.ui.screen.Article.ArticleLayout
import com.example.openmind.ui.screen.ArticleList.ArticleListLayout

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
        composable(Screen.ArticleListScreen.route) {
            BaseComposeScreen(
                navController = navController,
                content = { controller, modifier ->
                    ArticleListLayout(
                        navController = controller,
                        modifier
                    )
                },
                toolbar = null,
                modifier = globalModifier,
                screenTitle = Screen.ArticleListScreen.title

            )

        }
        composable(Screen.ArticleScreen.route) {
            BaseComposeScreen(
                navController = navController,
                content = { controller, modifier ->
                    ArticleLayout(
                        navController = controller,
                        modifier
                    )
                },
                toolbar = null,
                modifier = globalModifier,
                screenTitle = Screen.ArticleScreen.title
            )
        }
        composable(Screen.CreateArticleScreen.route) {
            CreateArticleLayout(navController = navController, screen = Screen.CreateArticleScreen)
        }
    }
}





