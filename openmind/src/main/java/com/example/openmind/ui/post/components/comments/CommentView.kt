package com.example.openmind.ui.post.components.comments

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.ui.components.general.RatingView
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.spacing

@Composable
fun CommentView(
    viewModel: PostViewModel,
    item: CommentModel,
    onReplyClick: (CommentModel) -> Unit
) {
    val isShowVisible = remember { mutableStateOf(true) }
    val currentLinesCount = remember { mutableIntStateOf(viewModel.getShortCommentLinesCount()) }

    val readMoreLabel = stringResource(id = R.string.read_more_label).lowercase()
    val showLessLabel = stringResource(id = R.string.show_less)

    val extendButtonLabel = remember { mutableStateOf(readMoreLabel) }
    val linesCount = remember { mutableIntStateOf(1) }

    val currentActiveCommentsCount = remember { mutableIntStateOf(0) }
    val rating = remember {
        RatingInfo(
            ratingId = item.ratingId,
            rating = mutableIntStateOf(item.rating),
            isRated = mutableIntStateOf(item.isRated)
        )
    }

    Row(modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)) {
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
                        text = item.commentAuthor,
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
                Text(
                    text = item.commentMessage,
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.sp,
                    color = DarkGray20,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = currentLinesCount.intValue,
                    onTextLayout = { textLayoutResult ->
                        linesCount.intValue = textLayoutResult.lineCount
                    }
                )
                if (linesCount.intValue > viewModel.getShortCommentLinesCount()) {
                    Text(
                        text = extendButtonLabel.value,
                        fontWeight = FontWeight.W400,
                        fontFamily = FontFamily.ManropeRegularW400,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = BorderLight,
                        maxLines = 1,
                        modifier = Modifier.clickable {
                            currentLinesCount.intValue =
                                if (currentLinesCount.intValue == viewModel.getShortCommentLinesCount()) Int.MAX_VALUE else viewModel.getShortCommentLinesCount()
                            extendButtonLabel.value =
                                if (extendButtonLabel.value == readMoreLabel) showLessLabel else readMoreLabel
                        }
                    )
                }
            }
            Row {
                RatingView(
                    rating = rating,
                    isComment = true,
                    onRatingChange = viewModel.onRatingChange()
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = MaterialTheme.spacing.small)
                ) {
                    Text(
                        text = "Reply",
                        fontFamily = FontFamily.ManropeBoldW700,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 16.sp,
                        maxLines = 1,
                        modifier = Modifier.clickable {
                            onReplyClick(item)
                        }
                    )
                }

            }
            if (item.subComments.isNotEmpty() && isShowVisible.value) {
                Text(
                    text = stringResource(
                        R.string.show_replies,
                        item.subComments.size
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaibPrimary,
                    modifier = Modifier.clickable {
                        isShowVisible.value = !isShowVisible.value
                        currentActiveCommentsCount.intValue =
                            (currentActiveCommentsCount.intValue + viewModel.getCommentBatchSize()) % item.subComments.size + 1
                    }
                )
            } else {
                item.subComments.take(currentActiveCommentsCount.intValue)
                    .forEachIndexed { index, subItem ->
                        when (index) {
                            (item.subComments.size) -> {
                                SubCommentView(
                                    subItem,
                                    onReplyClick = onReplyClick,
                                    onRatingChange = viewModel.onRatingChange()
                                )
                                Text(
                                    text = stringResource(
                                        R.string.hide_replies,
                                        item.subComments.size
                                    ),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaibPrimary,
                                    modifier = Modifier.clickable {
                                        isShowVisible.value = !isShowVisible.value
                                        currentActiveCommentsCount.intValue = 0
                                    }
                                )
                            }

                            currentActiveCommentsCount.intValue -> {
                                SubCommentView(
                                    subItem,
                                    onReplyClick = onReplyClick,
                                    onRatingChange = viewModel.onRatingChange()
                                )
                                Text(
                                    text = stringResource(
                                        R.string.show_replies,
                                        item.subComments.size - currentActiveCommentsCount.intValue
                                    ),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaibPrimary,
                                    modifier = Modifier.clickable {
                                        currentActiveCommentsCount.intValue =
                                            (currentActiveCommentsCount.intValue + viewModel.getCommentBatchSize())
                                    }
                                )
                            }

                            else -> {
                                SubCommentView(
                                    subItem,
                                    onReplyClick = onReplyClick,
                                    onRatingChange = viewModel.onRatingChange()
                                )
                            }
                        }

                    }
            }
        }
    }
}
