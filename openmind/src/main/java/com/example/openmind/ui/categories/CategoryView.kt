package com.example.openmind.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.domain.model.category.CategoryDto
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.spacing

@Composable
fun CategoryView(
    base64ToImageBitmapConverter: ((String?) -> ImageBitmap?)? = null,
    navigateToPostList: () -> Unit,
    categoryDto: CategoryDto,
) {
    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = categoryDto.categoryTitle,
            style= MaterialTheme.typography.titleLarge,
            maxLines = 1,
            color = DarkGray20,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
        ) {

            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navigateToPostList.invoke()
                }, contentAlignment = Alignment.CenterStart) {

                val image = base64ToImageBitmapConverter?.invoke(categoryDto.categoryImage)
                if (image != null) {
                    Image(
                        bitmap = image,
                        contentDescription = "navigate",

                        )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp)
                ) {
                    Text(
                        text = "${categoryDto.postCount} posts",
                        fontFamily = FontFamily.ManropeRegularW400,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = changeWordStyle(
                            categoryDto.tagLine,
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