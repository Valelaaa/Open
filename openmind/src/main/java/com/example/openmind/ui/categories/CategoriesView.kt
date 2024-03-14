package com.example.openmind.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.ui.categories.viewModel.CategoriesViewModel
import com.example.openmind.ui.components.general.TopAppBarOpenMind
import com.example.openmind.ui.navigation.navigateToPostList
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400

@Composable
fun CategoriesView(
    viewModel: CategoriesViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        LazyColumn() {
            items(items = viewModel.getCategoriesList()) { category ->
                CategoryView(navController, category)
            }
        }
    }
}

@Composable
fun CategoryView(navController: NavController, categoryInfo: CategoryInfo) {
    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = categoryInfo.categoryTitle,
            fontFamily = FontFamily.ManropeBoldW700,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            maxLines = 1,
            color = DarkGray20,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .background(categoryInfo.backgroundStyle, shape = RoundedCornerShape(8.dp))
                .clickable {
                    navController.navigateToPostList(categoryInfo.categoryType)
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 22.dp)
            ) {
                Text(
                    text = categoryInfo.categoryDescription.orEmpty(),
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 14.sp,
                    maxLines = 2,
                    lineHeight = 20.sp,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = categoryInfo.postCount.toString() + " articles",
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Color.DarkGray,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CategoriesViewPreview() {
    Scaffold(topBar = {
        TopAppBarOpenMind(
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
