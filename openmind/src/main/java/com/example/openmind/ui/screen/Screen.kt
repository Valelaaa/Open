package com.example.openmind.ui.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.categories.CategoriesView
import com.example.openmind.ui.categories.components.CategoriesAppBar
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.BasicTopAppBar
import com.example.openmind.ui.create_post.CreatePostContentView
import com.example.openmind.ui.create_post.components.TopAppBarCreatePost
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.post.PostContentView
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.post_list.PostListContentView
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.search_result.SearchResultContentView
import com.example.openmind.ui.search_result.viewModel.SearchResultViewModel
import com.example.openmind.ui.success_post_register.SuccessRegisteredPostViewModel
import com.example.openmind.ui.success_post_register.SuccessRegistrationPostView

sealed class Screen<T : ViewModel>(
    val route: String = "", var title: String = "",
    val topAppBar: @Composable (T, NavController) -> Unit,
    val content: @Composable (T, NavController, Map<String, String>, Modifier) -> Unit,
    //Map for args
    val viewModelClass: Class<T>
) {
    object CategoriesScreen : Screen<CategoriesViewModel>(
        route = "categories_screen", title = "Categories",
        viewModelClass = CategoriesViewModel::class.java,
        topAppBar = { viewModel, navController ->
            CategoriesAppBar(
                viewModel = viewModel,
                navController = navController,
                screen = CategoriesScreen
            )
        },
        content = { viewModel, navController, _, modifier ->
            CategoriesView(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
    )

    object PostListScreen : Screen<PostListViewModel>(
        route = "post_list_screen", title = "Posts",
        viewModelClass = PostListViewModel::class.java,
        topAppBar = { viewModel, navController ->
            BasicTopAppBar(
                viewModel = viewModel,
                navController = navController,
                currentScreen = PostListScreen,
            )
        },
        content = { viewModel, navController, args, modifier ->
            val category = args["category"]
            viewModel.setCategory(
                PostCategories.valueOf(category.orEmpty().uppercase())
            )
            Log.d(
                "Screen", "Category set to $category"
            )
            viewModel.setActiveCategoryInfo()
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
            BasicTopAppBar(
                viewModel = viewModel,
                navController = navController,
                currentScreen = PostScreen
            )
        },
        content = { viewModel, _, args, modifier ->
            val postId = args["postId"]
            Log.d(
                "Screen", "postId set to $postId"
            )
            viewModel.setCurrentPostID(postId = postId.orEmpty())
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
            TopAppBarCreatePost(
                navController = navController,
                viewModel = viewModel,
                CreatePostScreen
            )

        },
        content = { viewModel, navController, args, modifier ->
            val category = args["category"]
            viewModel.setCategory(category?.let { PostCategories.valueOf(it.uppercase()) }
                ?: PostCategories.BUG)
            Log.d(
                "Screen", "Category set to $category"
            )
            CreatePostContentView(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier
            )
        }
    )

    object SearchResultsScreen : Screen<SearchResultViewModel>(
        route = "search_result_screen", title = "Results",
        viewModelClass = SearchResultViewModel::class.java,
        topAppBar = { viewModel, navController ->
            BasicTopAppBar(
                viewModel = viewModel,
                navController = navController,
                currentScreen = SearchResultsScreen
            )

        }, content = { viewModel, navController, args, modifier ->
            val query = args["searchQuery"]
            val category = args["category"]
            viewModel.searchWithQuery(query.orEmpty(), category.orEmpty())
            SearchResultContentView(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier,
            )
        }
    )

    object SuccessRegisteredPostScreen : Screen<SuccessRegisteredPostViewModel>(
        route = "registered_post_screen", title = "registered_post",
        viewModelClass = SuccessRegisteredPostViewModel::class.java,
        topAppBar = { _, _ -> },
        content = { viewModel, navController, _, modifier ->
            SuccessRegistrationPostView(
                viewModel, navController, modifier
            )
        }
    )
}