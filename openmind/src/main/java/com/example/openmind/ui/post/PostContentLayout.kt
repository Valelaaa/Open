package com.example.openmind.ui.post

import NoRippleInteractionSource
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.post.Post
import com.example.openmind.data.post.User
import com.example.openmind.data.post.UserComment
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.post.PostRating
import com.example.openmind.ui.components.post.SharePost
import com.example.openmind.ui.components.postlist.post.tag
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostContentLayout(
    navController: NavController,
    currentPost: Post,
    modifier: Modifier = Modifier
) {
    val category = Categories.BUG
    val comment = remember {
        UserComment(User("@anamaria"), "")
    }
    var commentMessage = remember {
        ""
    }
    Box(modifier = modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 23.dp , horizontal = 28.dp)
                .clickable(onClick = {
                    /*TODO(NavigateToPost)*/
                    navController.navigate(Screen.PostScreen.route)
                    Log.d(tag, "Navigate to Post: ${currentPost.postId}")
                })
        ) {
            Column(
                modifier = Modifier

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
                                .size(30.dp)
                                .padding(start = 0.dp, end = 7.dp, top = 6.dp)
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

                //Post Title
                Text(
                    text = currentPost.title.take(300), fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily.ManropeBoldW700,
                    color = DarkBlue40,
                )
                //Post Description
                Text(
                    text = currentPost.description, fontSize = 12.sp,
                    lineHeight = 16.sp, fontFamily = FontFamily.ManropeRegularW400,
                    modifier = Modifier.padding(top = 6.dp)
                )
                //Future Implementation
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80.dp)
                        .background(Color.Transparent)
                        .padding(top = 14.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BorderLight, RoundedCornerShape(8.dp))
                ) {}


                // FeedBack and Share
                Row(
                    modifier = Modifier
                        .padding(top = 14.dp, bottom = 12.dp)
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
            //TODO(CommentsSection)
            Column(modifier = Modifier.padding(top = 28.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, BorderLight, RoundedCornerShape(6.dp))
                        .padding(vertical = 5.dp, horizontal = 10.dp),
//                        .clip(RoundedCornerShape(6.dp))
//                        .defaultMinSize(minHeight = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        "userPicture",
                        modifier = Modifier
                            .size(30.dp)
                    )
//                        CustomTextField(
//                            value = commentMessage, onValueChange = { commentMessage = it },
//                            placeholder = {
//                                Text(
//                                    text = stringResource(R.string.comment_placeholder),
//                                    color = IconColor,
//                                    fontFamily = FontFamily.ManropeSemiBoldW600,
//                                    fontSize = 16.sp,
//                                    lineHeight = 24.sp,
//                                )
//                            },
//
//                        )
//                    }
                    OutlinedTextField(
                        value = commentMessage,
                        onValueChange = { commentMessage = it },
                        shape = RoundedCornerShape(6.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
//                                text = stringResource(R.string.comment_placeholder),
                                text = "Comments...",
                                color = IconColor,
                                fontFamily = FontFamily.ManropeSemiBoldW600,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                            )
                        },
                        modifier = Modifier
                            .defaultMinSize(minHeight = 40.dp)
                            .padding(start = 14.dp, top = 0.dp, bottom = 0.dp),

                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostContentLayoutPreview() {
    val navController = NavController(LocalContext.current)
    val post = PostRepositoryProvider.provideRepository().getMockPost()
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Black,
            )
        )
    }) { scaffoldPaddings ->
        PostContentLayout(
            navController = navController,
            currentPost = post,
            modifier = Modifier.padding(scaffoldPaddings)
        )
    }
}