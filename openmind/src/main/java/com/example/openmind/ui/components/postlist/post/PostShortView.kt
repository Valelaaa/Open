package com.example.openmind.ui.components.postlist.post

import NoRippleInteractionSource
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.createpost.CreatePostViewModel
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.post.PostRating
import com.example.openmind.ui.components.post.SharePost
import com.example.openmind.ui.navigation.navigateToPost
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60

const val tag = "PostShortView"

@Composable
fun PostShortView(
    navController: NavController,
    postId: String,
    category: Categories,
    modifier: Modifier = Modifier,
) {
    val currentPost = remember {
        PostRepositoryProvider.provideRepository().getPostById(postId)
    }

    Row(
        modifier
            .fillMaxWidth()
            .clickable(onClick = {
                /*TODO(NavigateToPost)*/
                navController.navigateToPost(postId = currentPost.postId)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 28.dp)
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
                            .padding(start = 0.dp, end = 8.dp, top = 6.dp)
                    )

                    //Category name
                    Text(
                        text = category.getStringValue(),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        fontFamily = FontFamily.ManropeSemiBoldW600,
                    )

                    //Created Time
                    Text(
                        text = currentPost.formatElapsedTime(),
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
//                        TODO("Open Hamburger menu for additional actions")
                        Log.d(tag, "Open Hamburger menu")

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

            // Post Content
            Column(
                modifier = Modifier
                    .padding(top = 8.dp),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Post Title
                    Text(
                        text = currentPost.title.trimIndent(), fontSize = 14.sp,
                        fontFamily = FontFamily.ManropeBoldW700,
                        color = DarkBlue40,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 20.dp)
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.contentdescription_more),
                        tint = SteelBlue60,
                        modifier = Modifier.padding(end = 5.dp)
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
                    PostRating(currentPost.postId, currentPost.rating, Modifier)
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
                                .clickable(onClick = {
                                    /*TODO("Navigate to Post -> scrollTo comments")*/
                                    Log.d(tag, "Navigate to Post -> scrollTo comments")
                                })
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
                                    currentPost.getCommentsCount()
                                ),
                                fontFamily = FontFamily.ManropeBoldW700,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    SharePost(currentPost.postId)
                }
            }
        }
    }
}


@Preview
@Composable
fun PostShortComposeViewPreview() {
    val post = CreatePostViewModel().createPost()
    val currentContext = LocalContext.current
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        PostShortView(
            navController = NavController(currentContext),
            postId = post.postId,
            category = Categories.BUG,
            modifier = Modifier
                .padding(horizontal = 28.dp, vertical = 30.dp)
                .background(LightText),
        )
    }
}