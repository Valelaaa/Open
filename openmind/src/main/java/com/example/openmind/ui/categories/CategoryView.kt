package com.example.openmind.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.domain.model.category.CategoryInfo
import com.example.openmind.ui.navigation.navigateToPostList
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400

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
                .clickable {
                    navController.navigateToPostList(categoryInfo.categoryType)
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = categoryInfo.backgroundImage),
                contentDescription = "navigate",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 22.dp)
            ) {
                Text(
                    text = categoryInfo.postCount.toString() + " articles",
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = changeWordStyle(
                        categoryInfo.tagLine,
                        0,
                        SpanStyle(fontFamily = FontFamily.ManropeBoldW700)
                    ),
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

fun changeWordStyle(text: String, wordIndex: Int, style: SpanStyle): AnnotatedString {
    val words = text.split(' ')
    require(wordIndex in words.indices) { "Invalid word index" }

    val builder = AnnotatedString.Builder()
    var startIndex = 0
    for (i in words.indices) {
        val word = words[i]
        val endIndex = startIndex + word.length
        if (i == wordIndex) {
            builder.withStyle(style) {
                append(word)
            }
        } else {
            builder.append(word)
        }
        if (i < words.size - 1) {
            builder.append(" ")
        }
        startIndex = endIndex + 1
    }

    return builder.toAnnotatedString()
}