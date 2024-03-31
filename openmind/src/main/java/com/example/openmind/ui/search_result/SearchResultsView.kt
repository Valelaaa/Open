package com.example.openmind.ui.search_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.post_list.components.PostShortView
import com.example.openmind.ui.search_result.viewModel.SearchResultViewModel
import com.example.openmind.ui.theme.Delimiter


@Composable
fun SearchResultContentView(
    navController: NavController,
    viewModel: SearchResultViewModel,
    modifier: Modifier = Modifier,
) {
    val searchResults = remember {
        viewModel.getSearchResults()
    }
    Box(modifier = modifier) {
        LazyColumn(Modifier.borderBottom(1.dp, Delimiter)) {
            items(items = searchResults,
                itemContent = { item ->
                    PostShortView(
                        navController = navController,
                        post = item,
                        onRatingChange = viewModel.onRatingChange()
                    )
                })
        }
    }

}
