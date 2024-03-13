package com.example.openmind.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.openmind.ui.categories.CategoriesView
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.TopAppBarOpenMind
import com.example.openmind.ui.components.post.CreateTopAppBarPost
import com.example.openmind.ui.create_post.CreatePostContentView
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.post.PostContentView
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.post_list.PostListContentView
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.search_result.SearchResultContentView
import com.example.openmind.ui.search_result.viewModel.SearchResultViewModel
import com.example.openmind.utils.PostCategories

sealed class Screen<T : ViewModel>(
    val route: String = "", val title: String = "",
    val topAppBar: @Composable (T, NavController) -> Unit,
    val content: @Composable (T, NavController, Map<String, String>, Modifier) -> Unit,
    //Map for args
    val viewModelClass: Class<T>
) {
    object CategoriesScreen : Screen<CategoriesViewModel>(
        route = "categories_screen", title = "Categories",
        viewModelClass = CategoriesViewModel::class.java,
        topAppBar = { viewModel, navController ->
            TopAppBarOpenMind(
                viewModel = viewModel,
                navController = navController,
                currentScreen = CategoriesScreen,
            )
        },
        content = { _, navController, _, modifier ->
            CategoriesView(navController = navController, modifier = modifier)
        }
    )

    object PostListScreen : Screen<PostListViewModel>(
        route = "post_list_screen", title = "Posts",
        viewModelClass = PostListViewModel::class.java,
        topAppBar = { viewModel, navController ->
            TopAppBarOpenMind(
                viewModel = viewModel,
                navController = navController,
                currentScreen = PostListScreen,
            )
        },
        content = { viewModel, navController, args, modifier ->
            val category = args["category"]
            viewModel.setCategory(category?.let { PostCategories.valueOf(it.uppercase()) }
                ?: PostCategories.BUG)

            PostListContentView(
                navController = navController, viewModel = viewModel,
                modifier = modifier
            )
        }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    object PostScreen : Screen<PostViewModel>(route = "post_screen", title = "Post",
        viewModelClass = PostViewModel::class.java,
        topAppBar = { viewModel, navController ->
            TopAppBarOpenMind(
                viewModel = viewModel,
                navController = navController,
                currentScreen = PostScreen
            )
        },
        content = { viewModel, _, args, modifier ->
            val postId = args["postId"]
            viewModel.setPost(postId = postId.orEmpty())
            PostContentView(
                viewModel = viewModel,
                modifier = modifier
            )
        }
    )

    object CreatePostScreen : Screen<CreatePostViewModel>(
        route = "create_post_screen", title = "Create Post",
        viewModelClass = CreatePostViewModel::class.java,
        topAppBar = { viewModel, navController ->
            CreateTopAppBarPost(navController = navController, createPostViewModel = viewModel)

        },
        content = { viewModel, _, args, modifier ->
            val category = args["category"]
            viewModel.setCategory(category?.let { PostCategories.valueOf(it.uppercase()) }
                ?: PostCategories.BUG)
            CreatePostContentView(viewModel = viewModel, modifier = modifier)
        }
    )

    object SearchResultsScreen : Screen<SearchResultViewModel>(
        route = "search_result_screen", title = "Results",
        viewModelClass = SearchResultViewModel::class.java,
        topAppBar = { viewModel, navController ->
            TopAppBarOpenMind(
                viewModel = viewModel,
                navController = navController,
                currentScreen = SearchResultsScreen
            )

        }, content = { viewModel, navController, args, modifier ->
            val query = args["searchQuery"]
            viewModel.onSearchTextChanged(query.orEmpty())
            SearchResultContentView(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier,
            )
        }
    )
}