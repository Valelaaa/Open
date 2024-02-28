package com.example.openmind.ui.post.components.comments

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.openmind.ui.theme.ManropeExtraBoldW800

fun withStylishTags(
    text: String, styleSpan: SpanStyle = SpanStyle(
        fontWeight = FontWeight.W800,
        fontFamily = FontFamily.ManropeExtraBoldW800,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp,
        color = Color.Black
    )
): AnnotatedString {
    val result = AnnotatedString.Builder()
    val delimiter = " "

    var words = text.split(delimiter)
    for (word in words) {
        if (word.indexOf("@") == 0) {
            result.withStyle(style = styleSpan) { append(word) }
        } else {
            result.append(word)
        }
        result.append(delimiter)
    }

    return result.toAnnotatedString()
}
