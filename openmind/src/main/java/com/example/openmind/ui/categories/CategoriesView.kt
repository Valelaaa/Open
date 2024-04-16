package com.example.openmind.ui.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.BasicTopAppBar
import com.example.openmind.ui.navigation.navigateToPostList
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.spacing

@Composable
fun CategoriesView(
    viewModel: CategoriesViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    if (viewModel.isLoading().value) {
        Box(
            modifier = modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large),
        ) {
            LazyColumn {
                items(items = viewModel.getCategoriesList()) { category ->
                    CategoryView(
                        navigateToPostList = {
                            navController.navigateToPostList(
                                PostCategories.valueOf(
                                    category.categoryName
                                )
                            )
                        },
                        base64ToImageBitmapConverter = { base64Image ->
                            base64Image?.let {
                                viewModel.stringToBitMap(it)
                                    .asImageBitmap()
                            }
                        },
                        categoryDto = category
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun CategoriesViewPreview() {
    Scaffold(topBar = {
        BasicTopAppBar(
            viewModel = viewModel(),
            navController = rememberNavController(),
            currentScreen = Screen.CategoriesScreen
        )
    }, content = { paddingValues ->
        CategoriesView(
            viewModel = CategoriesViewModel(),
            navController = rememberNavController(),
            modifier = Modifier.padding(paddingValues)
        )
    })
}
