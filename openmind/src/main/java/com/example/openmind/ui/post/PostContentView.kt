package com.example.openmind.ui.post

import CommentField
import NoRippleInteractionSource
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.post.EmptyPost
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.post.RatingView
import com.example.openmind.ui.components.post.SharePost
import com.example.openmind.ui.components.postlist.post.tag
import com.example.openmind.ui.components.postlist.selectSortingType.SortingSelector
import com.example.openmind.ui.post.components.comments.CommentView
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmind.utils.Categories
import com.example.openmind.utils.Sortable


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T> PostContentView(
    navController: NavController,
    category: Categories = Categories.BUG,
    postId: String,
    viewModel: T,
    modifier: Modifier = Modifier
) where T : PostViewModel, T : Sortable {
    val currentPost = remember {
        if (viewModel.getPostById(postId) != null) viewModel.getPostById(postId)!! else EmptyPost
    }
    val comments = remember {
        viewModel.getComments()
    }
    val postRating = remember { currentPost.ratingInfo }
    var commentToReply = remember { mutableStateOf<Comment?>(null) }
    Column(
        modifier = modifier
            .padding(start = 28.dp, end = 28.dp, bottom = 5.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            Modifier
                .weight(1f)
        ) {
            item {
                Column {
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
                            text = currentPost.description,
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
                            RatingView(postRating, Modifier)
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
                                            Log.d(
                                                tag,
                                                "Navigate to Post -> scrollTo comments"
                                            )
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
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        SortingSelector(
                            sortableViewModel = viewModel,
                            contentPaddings = PaddingValues(0.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }
            }
            //TODO(CommentsSection)
            items(items = comments) { item ->
                CommentView(
                    viewModel = viewModel,
                    item = item,
                    onReplyClick = { comment ->
                        commentToReply.value = comment
                    })
            }
        }
        CommentField(viewModel = viewModel, replyTo = commentToReply)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showSystemUi = true
)
@Composable
fun PostContentViewPreview() {
    val navController = NavController(LocalContext.current)
    val viewModel = PostViewModel()
    val postViewModel = PostListViewModel()
    val post =
        postViewModel.getPostList().first { post: Post -> post.getCommentsCount() != 0 }
    PostContentView(
        navController = navController,
        postId = post.postId,
        viewModel = viewModel
    )

}