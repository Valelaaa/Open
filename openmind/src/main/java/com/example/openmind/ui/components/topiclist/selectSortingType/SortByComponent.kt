package com.example.openmind.ui.components.topiclist.selectSortingType

import NoRippleInteractionSource
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
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

@OptIn(ExperimentalMaterial3Api::class)
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
    Box(
        modifier = modifier
            .background(LightGray80, shape = RoundedCornerShape(50))
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .defaultMinSize(minHeight = 22.dp)
                .padding(vertical = 1.dp)
        ) {
            sortTypes.forEach { item ->
                Column {
                    CompositionLocalProvider(
                        LocalMinimumTouchTargetEnforcement provides false,
                    ) {
                        Button(
                            onClick = {
                                topicListViewModel.setActiveSortType(item)
                            },
                            colors = if (topicListViewModel.activeSortType.value == item)
                                activeColors
                            else inactiveColors,
                            modifier = Modifier
                                .defaultMinSize(minWidth = 40.dp, minHeight = 20.dp)
                                .padding(0.dp),
                            interactionSource = NoRippleInteractionSource.INSTANCE,
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(
                                0.dp
                            )

                        ) {
                            Text(
                                text = item.string,
                                fontFamily = FontFamily.ManropeRegularW400,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
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