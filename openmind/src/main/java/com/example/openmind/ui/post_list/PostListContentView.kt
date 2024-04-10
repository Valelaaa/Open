package com.example.openmind.ui.post_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.domain.model.category.PostCategories
import com.example.openmind.ui.components.general.SortingSelector
import com.example.openmind.ui.navigation.navigateToCreatePost
import com.example.openmind.ui.post_list.components.PostShortView
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.NightBlue
import com.example.openmind.ui.theme.SteelGray

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PostListContentView(
    navController: NavController,
    viewModel: PostListViewModel,
    modifier: Modifier = Modifier
) {
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            viewModel.fetchPostList()
            state.endRefresh()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(state.nestedScrollConnection),
    ) {
        LazyColumn() {
            item {
                Column {
                    Row(modifier = Modifier.padding(end = 30.dp, start = 30.dp, bottom = 10.dp)) {
                        Box(modifier = Modifier.padding(end = 13.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(viewModel.getActiveCategoryInfo().backgroundStyle),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = viewModel.getCategoryInitials(),
                                    fontFamily = FontFamily.ManropeSemiBoldW600,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    textAlign = TextAlign.Center,
                                    color = LightText
                                )
                            }
                        }
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = viewModel.getActiveCategoryInfo().categoryTitle,
                                    fontFamily = FontFamily.ManropeBoldW700,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    color = Color.Black
                                )
                                Icon(
                                    Icons.Default.MoreVert,
                                    contentDescription = stringResource(id = R.string.contentdescription_more),
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {

                                        }
                                        .rotate(90f)
                                )
                            }
                            Column {
                                Text(
                                    text = viewModel.getPostCount(),
                                    fontFamily = FontFamily.ManropeRegularW400,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp,
                                    color = SteelGray,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Text(
                                    text = viewModel.getActiveCategoryInfo().categoryDescription.orEmpty(),
                                    fontFamily = FontFamily.ManropeRegularW400,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = NightBlue
                                )
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SortingSelector(
                            viewModel,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                            contentPaddings = PaddingValues(vertical = 8.dp, horizontal = 10.dp)
                        )
//                        Button(
//                            onClick = {
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = LightGray80,
//                                contentColor = SteelBlue60
//                            ),
//                            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 15.dp),
//                            shape = RoundedCornerShape(50),
//                            modifier = Modifier
//                                .defaultMinSize(minHeight = 1.dp)
//                                .padding(top = 7.dp, bottom = 10.dp, start = 18.dp)
//                                .align(Alignment.Top)
//                        ) {
//                            Row() {
//                                Text(
//                                    text = "A to Z",
//                                    fontFamily = FontFamily.ManropeRegularW400,
//                                    fontSize = 14.sp,
//                                    color = SteelBlue60,
//                                    modifier = Modifier.padding(horizontal = 24.dp),
//                                )
//                                Box(
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Icon(
//                                        Icons.Default.KeyboardArrowDown,
//                                        contentDescription = "sorting direction",
//                                        tint = SteelBlue60,
//
//                                        modifier = Modifier
//                                            .size(20.dp)
//                                    )
//                                }
//                            }
//                        }
                    }
                }
            }
            if (viewModel.postsIsLoading()) {
                item {
                    Box(
                        modifier = modifier
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(40.dp))
                    }
                }
            } else {
                if (!state.isRefreshing) {
                    items(items = viewModel.getPostList(), key = { it.hashCode() },
                        itemContent = { item ->
                            PostShortView(
                                navController = navController,
                                post = item,
                                modifier = Modifier.animateItemPlacement(),
                                viewModel.onRatingChange()
                            )
                        }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp, shape = RoundedCornerShape(50), clip = true,
                        ambientColor = Color(0x3D222222),
                    )
                    .clip(RoundedCornerShape(50))
                    .clickable {
                        navController.navigateToCreatePost(viewModel.getPostCategory())
                    }
                    .background(Color(0xFF66CDBD))
                    .size(60.dp)
                    .padding(12.dp),
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "create",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchPostList()
    }
}


@Preview
@Composable
fun PostListPreview() {
    val navController = NavController(LocalContext.current)
    val viewModel = PostListViewModel()
    viewModel.setCategory(PostCategories.BUG)
    viewModel.setActiveCategoryInfo()

    PostListContentView(
        navController = navController,
        viewModel = viewModel(),
    )
}