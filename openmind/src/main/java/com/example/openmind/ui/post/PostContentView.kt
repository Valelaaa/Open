package com.example.openmind.ui.post

import CommentField
import NoRippleInteractionSource
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.ui.components.general.RatingView
import com.example.openmind.ui.components.general.SharePost
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.post.components.comments.CommentView
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmind.ui.theme.spacing


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostContentView(
    viewModel: PostViewModel,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(
                start = MaterialTheme.spacing.large,
                end = MaterialTheme.spacing.large,
                bottom = 5.dp
            )
            .fillMaxSize()
    ) {
        if (viewModel.postIsLoading() /*and viewModel.isCommentsLoading()*/) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        } else {
            LazyColumn(
                Modifier.weight(1f)
            ) {

                item {

                    Column {
                        Column(
                            modifier = Modifier.borderBottom(1.dp, Delimiter)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    //UserIcon
                                    Image(
                                        painter = painterResource(R.drawable.user_pic),
                                        contentDescription = "user_icon",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(start = 0.dp, end = 7.dp, top = 6.dp)
                                    )

                                    //Category name
                                    Text(
                                        text = viewModel.getPost().category,
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        maxLines = 1,
                                        fontFamily = FontFamily.ManropeSemiBoldW600,
                                    )

                                    //Created Time
                                    Text(
                                        text = viewModel.getPost().formatElapsedTime(),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        maxLines = 1,
                                        fontFamily = FontFamily.ManropeRegularW400,
                                        modifier = Modifier.padding(start = 20.dp),
                                        color = SteelBlue60
                                    )
                                }
                                //more button (three dots)
                                IconButton(
                                    onClick = {
                                    },
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 10.dp),
                                    interactionSource = NoRippleInteractionSource.INSTANCE
                                ) {
                                    Icon(
                                        Icons.Default.MoreVert,
                                        contentDescription = stringResource(id = R.string.contentdescription_more),
                                        modifier = Modifier
                                            .size(24.dp)
                                            .rotate(90f)
                                    )
                                }
                            }

                            //Post Title
                            Text(
                                text = viewModel.getPost().title, fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily.ManropeBoldW700,
                                color = DarkBlue40,
                            )
                            //Post Description
                            Text(
                                text = viewModel.getPost().description,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontFamily = FontFamily.ManropeRegularW400,
                                modifier = Modifier.padding(top = 6.dp)
                            )

                            // FeedBack and Share
                            Row(
                                modifier = Modifier
                                    .padding(top = 14.dp, bottom = 12.dp)
                                    .fillMaxWidth(),
                            ) {
                                //Rating
                                RatingView(
                                    viewModel.getPostRating(),
                                    Modifier,
                                    onRatingChange = viewModel.onRatingChange()
                                )
                                //Comments
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .weight(1f)

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
                                            contentDescription = stringResource(id = R.string.contentdescription_decrease),
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
                                            text = stringResource(
                                                R.string.comments_count,
                                                viewModel.getPost().commentCount
                                            ),
                                            fontFamily = FontFamily.ManropeBoldW700,
                                            fontSize = 14.sp,
                                            lineHeight = 20.sp,
                                            maxLines = 1,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                SharePost(viewModel.getPost().postId)
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(items = viewModel.getComments()) { item ->
                    CommentView(
                        viewModel = viewModel,
                        item = item,
                        onReplyClick = { comment ->
                            viewModel.setReplyComment(comment)
                        })
                }
            }

        }
        LaunchedEffect(Unit) {
            viewModel.fetchPostDetails()
        }
        CommentField(viewModel = viewModel, replyTo = viewModel.getReplyComment())
    }
}
