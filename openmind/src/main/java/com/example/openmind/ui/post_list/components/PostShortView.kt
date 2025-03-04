package com.example.openmind.ui.post_list.components

import NoRippleInteractionSource
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.domain.model.post.ShortPostDto
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.ui.components.general.RatingView
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmind.ui.theme.spacing
import com.example.openmind.utils.numberFormatted
import com.example.openmind.ui.components.general.SharePost

const val tag = "PostShortView"

@Composable
fun PostShortView(
    post: ShortPostDto,
    modifier: Modifier = Modifier,
    onRatingChange: (String, Int) -> Unit,
    navigateToPost: () -> Unit
) {
    val ratingInfo = remember {
        RatingInfo(
            ratingId = post.ratingId,
            rating = mutableIntStateOf(post.postRating ?: 0),
            isRated = mutableIntStateOf(post.isRated)
        )
    }
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small)
            .clickable(onClick = {
                navigateToPost.invoke()
            })
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.large)
                .borderBottom(1.dp, Delimiter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    //UserIcon
                    Image(
                        painter = painterResource(R.drawable.user_pic),
                        contentDescription = "user_icon",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 0.dp, end = MaterialTheme.spacing.small, top = 6.dp)
                    )

                    //Category name
                    Text(
                        text = post.creatorName ?: "johndoe",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        fontFamily = FontFamily.ManropeSemiBoldW600,
                    )

                    //Created Time
                    Text(
                        text = post.formatElapsedTime(),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        fontFamily = FontFamily.ManropeRegularW400,
                        modifier = Modifier.padding(start = 20.dp),
                        color = SteelBlue60
                    )
                }
//                more button (three dots)
                IconButton(
                    onClick = {
                        Log.d(tag, "Open Hamburger menu")

                    },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 10.dp),
                    interactionSource = NoRippleInteractionSource.INSTANCE
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = stringResource(id = R.string.content_description_more),
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(90f)
                    )
                }
            }

            // Post Content
            Column(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.small),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Post Title
                    Text(
                        text = post.postTitle!!.trimIndent(), fontSize = 14.sp,
                        fontFamily = FontFamily.ManropeBoldW700,
                        color = DarkBlue40,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )

                }
            }
            // FeedBack and Share
            Column {
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                ) {
                    //Rating
                    RatingView(rating = ratingInfo, Modifier, onRatingChange = onRatingChange)
                    //Comments
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, BorderLight, CircleShape)
                                .padding(end = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = stringResource(id = R.string.content_description_decrease),
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        top = 5.dp,
                                        bottom = 5.dp,
                                        end = 6.dp
                                    )
                                    .size(20.dp),
                                tint = MaibPrimary
                            )
                            Text(
                                text = numberFormatted(post.commentsCount),
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    SharePost(postId = post.postId)
                }
            }
        }
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Composable
fun PostShortViewPreview(){

    PostShortView(
        post = ShortPostDto(postId = "postId", postTitle = "title", creatorName = "John"),
        onRatingChange = {_,_->},
        navigateToPost = {}
    )
}

