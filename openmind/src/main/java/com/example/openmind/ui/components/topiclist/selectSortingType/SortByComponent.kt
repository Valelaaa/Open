package com.example.openmind.ui.components.topiclist.selectSortingType

import NoRippleInteractionSource
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openmind.data.viewModel.SortBy
import com.example.openmind.data.viewModel.TopicListViewModel
import com.example.openmind.ui.theme.LightGray80
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.colorthemes.ColorDarkTokens
import com.example.openmind.ui.theme.colorthemes.ColorLightTokens
import com.example.openmind.ui.theme.colorthemes.ColorTokens

@Composable
fun SortByComponent(topicListViewModel: TopicListViewModel, modifier: Modifier = Modifier) {
    val sortTypes = SortBy.values()

    val colorTokens: ColorTokens = when {
        isSystemInDarkTheme() -> ColorDarkTokens
        else -> ColorLightTokens
    }
    val activeColors = ButtonDefaults.buttonColors(
        containerColor = colorTokens.RatioActiveBackground,
        contentColor = colorTokens.RatioActiveContent
    )
    val inactiveColors = ButtonDefaults.buttonColors(
        containerColor = colorTokens.RatioInactiveBackground,
        contentColor = colorTokens.RatioInactiveContent
    )
    Column(
        modifier = modifier
            .background(Color.Transparent),

        ) {
        Box(
            modifier = Modifier
                .background(LightGray80, shape = RoundedCornerShape(24.dp))
                .height(24.dp)
//                .defaultMinSize(minHeight = 42.dp)
//                .padding(horizontal = 1.dp)
        ) {

            LazyRow {
                items(items = sortTypes, itemContent = { item ->
                    Button(
                        onClick = {
                            topicListViewModel.setActiveSortType(item)
                        },
                        colors = if (topicListViewModel.activeSortType.value == item)
                            activeColors
                        else inactiveColors,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 40.dp, minHeight = 20.dp)
                            .padding(top = 1.dp, bottom = 1.dp),
                        interactionSource = NoRippleInteractionSource(),
                        shape = RoundedCornerShape(24.dp),
                        contentPadding = PaddingValues(
//                            top = 10.dp,
//                            bottom = 10.dp,
//                            start = 20.dp,
//                            end = 20.dp
                        )

                    ) {
                        Text(
                            text = item.string,
                            fontFamily = FontFamily.ManropeRegularW400,
                            fontSize = 14.sp
                        )
                    }
                })
            }
        }
    }

}

@Preview
@Composable
fun SortByComponentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(vertical = 40.dp, horizontal = 40.dp)
    ) {

        SortByComponent(topicListViewModel = viewModel())
    }
}