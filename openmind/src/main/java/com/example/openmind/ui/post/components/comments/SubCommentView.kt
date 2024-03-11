package com.example.openmind.ui.post.components.comments

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.ui.components.post.RatingView
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeExtraBoldW800
import com.example.openmind.ui.theme.ManropeRegularW400

@Composable
fun SubCommentView(item: Comment, onReplyClick: (Comment) -> Unit) {
    val defaultMaxLine = remember { mutableIntStateOf(3) }

    val readMoreLabel = stringResource(id = R.string.read_more_label).lowercase()
    val showLessLabel = stringResource(id = R.string.show_less)
    val extendButtonLabel = remember { mutableStateOf(readMoreLabel) }
    val linesCount = remember { mutableIntStateOf(1) }
    val rating = remember { item.ratingInfo }
    val tagSpanStyle = SpanStyle(
        fontWeight = FontWeight.W800,
        fontFamily = FontFamily.ManropeExtraBoldW800,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        color = Color.Black
    )


    Row {
        Row(
            modifier = Modifier
                .padding(end = 10.dp)
        ) {
            Icon(
                Icons.Default.AccountCircle,
                "user",
                tint = MaibPrimary,
                modifier = Modifier.size(30.dp)
            )

        }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = item.author.nickname,
                        fontFamily = FontFamily.ManropeBoldW700,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = DarkGray20,
                        fontWeight = FontWeight.W700
                    )
                    Text(
                        text = item.formatElapsedTime(),
                        fontFamily = FontFamily.ManropeRegularW400,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.W400,
                        color = BorderLight,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(20.dp)) {
                    Icon(
                        Icons.Default.MoreVert,
                        "more",
                        modifier = Modifier.rotate(90f)
                    )
                }
            }
            Column {
//                                TODO("COMMENT MESSAGE (SHORT),  READ-MORE(EXTEND MESSAGE)")
                Text(
                    text =
                    if (item.message.contains("@")) {
                        withStylishTags(
                            item.message, tagSpanStyle
                        )
                    } else {
                        AnnotatedString(item.message)
                    },
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.sp,
                    color = DarkGray20,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = defaultMaxLine.intValue,
                    onTextLayout = { textLayoutResult ->
                        linesCount.intValue = textLayoutResult.lineCount
                    }
                )
                if (linesCount.intValue >= 3) {
                    Text(
                        text = extendButtonLabel.value,
                        fontWeight = FontWeight.W400,
                        fontFamily = FontFamily.ManropeRegularW400,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = BorderLight,
                        maxLines = 1,
                        modifier = Modifier.clickable {
                            defaultMaxLine.intValue =
                                if (defaultMaxLine.intValue == 3) Int.MAX_VALUE else 3
                            extendButtonLabel.value =
                                if (extendButtonLabel.value == readMoreLabel) showLessLabel else readMoreLabel
                        }
                    )
                }
            }
            Row {
                RatingView(
                    rating = rating,
                    isComment = true
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = "Reply",
                        fontFamily = FontFamily.ManropeBoldW700,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 16.sp,
                        maxLines = 1,
                        modifier = Modifier.clickable {
                            Log.i("SubCommentView", "Clicked reply to ${item.author}'s comment ")
                            onReplyClick(item)
                        }
                    )
                }
            }
        }
    }
}