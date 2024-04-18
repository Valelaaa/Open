package com.example.openmind.ui.components.general

import NoRippleInteractionSource
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.ui.theme.LightGray80
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.colorthemes.ColorDarkTokens
import com.example.openmind.ui.theme.colorthemes.ColorLightTokens
import com.example.openmind.ui.theme.colorthemes.ColorTokens
import com.example.openmind.ui.theme.spacing
import com.example.openmind.utils.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingSelector(
    setActiveSortType: (SortType) -> Unit,
    activeSortType: SortType,
    sortingList: List<SortType>,
    modifier: Modifier = Modifier,
    contentPaddings: PaddingValues = PaddingValues(
        vertical = MaterialTheme.spacing.small,
        horizontal = 10.dp
    )
) {
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
        ) {
            sortingList.forEach { item ->
                Column {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false,
                    ) {
                        Button(
                            onClick = {
                                setActiveSortType.invoke(item)
                            },
                            colors = if (activeSortType == item)
                                activeColors
                            else inactiveColors,
                            modifier = Modifier
                                .defaultMinSize(minWidth = 60.dp, minHeight = 30.dp)
                                .padding(1.dp),
                            interactionSource = NoRippleInteractionSource.INSTANCE,
                            shape = RoundedCornerShape(50),
                            contentPadding = contentPaddings

                        ) {
                            Text(
                                text = item.string,
                                fontFamily = FontFamily.ManropeRegularW400,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

