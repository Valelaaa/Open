package com.example.openmind.ui.post

import NoRippleInteractionSource
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.post.EmptyPost
import com.example.openmind.data.post.Post
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.Sortable
import com.example.openmind.data.viewModel.post.PostViewModel
import com.example.openmind.data.viewModel.postlist.PostListViewModel
import com.example.openmind.ui.components.general.CustomTextField
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.components.post.RatingLayout
import com.example.openmind.ui.components.post.SharePost
import com.example.openmind.ui.components.postlist.post.tag
import com.example.openmind.ui.components.postlist.selectSortingType.SortingSelector
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.Delimiter
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PostContentLayout(
    navController: NavController,
    category: Categories = Categories.BUG,
    postId: String,
    viewModel: T,
    modifier: Modifier = Modifier
) where T : PostViewModel, T : Sortable {
    val currentPost = remember {
        if (viewModel.getPostById(postId) != null) viewModel.getPostById(postId)!! else EmptyPost

    }
    var commentMessage = remember {
        mutableStateOf("")
    }
    val repository = PostRepositoryProvider.provideRepository()
    val comments = remember {
        runBlocking {
            if (repository.getById(postId).firstOrNull() != null)
                repository.getById(postId).firstOrNull()!!.comments
            else EmptyPost.comments
        }
    }
    LazyColumn(
        modifier = modifier.padding(horizontal = 28.dp)
    ) {
        item {
            Column(
                Modifier
                    .fillMaxSize()

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

                    // FeedBack and Share
                    Row(
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 12.dp)
                            .fillMaxWidth(),
                    ) {
                        //Rating
                        RatingLayout(currentPost.postId, currentPost.rating, Modifier)
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
        //TODO(CommentsSection)

        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .border(2.dp, BorderLight, RoundedCornerShape(6.dp))
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    CustomTextField(
                        value = commentMessage.value, onValueChange = { commentMessage.value = it },
                        shape = RoundedCornerShape(6.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.Transparent,
                        ),
                        contentPadding = PaddingValues(5.dp),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.comment_placeholder),
                                color = IconColor,
                                fontFamily = FontFamily.ManropeSemiBoldW600,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        modifier = Modifier
                            .defaultMinSize(minHeight = 40.dp)
                            .padding(start = 14.dp, top = 5.dp, bottom = 5.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily.ManropeSemiBoldW600,
                            lineHeight = 24.sp,
                            color = DarkGray20,
                            textAlign = TextAlign.Justify
                        ),
                    )
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


//
                }
            }
        }
        items(items = comments) { item ->
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
//                                TODO("COMMENT MESSAGE (SHORT),  READMORE_(EXTEND MESSAGE)")
                        Text(
                            text = item.message,
                            fontFamily = FontFamily.ManropeRegularW400,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            lineHeight = 16.sp,
                            color = DarkGray20,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )
                        Text(
                            text = stringResource(R.string.read_more_label).lowercase(),
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily.ManropeRegularW400,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            color = BorderLight,
                            maxLines = 1
                        )
                    }
                    Row {
                        RatingLayout(
                            id = item.commentId.toString(),
                            rating = item.rating,
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
                                maxLines = 1
                            )
                        }

//                                TODO("RATING SECTION, REPLY")
                    }
//                            TODO("REPLY")
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostContentLayoutPreview() {
    val navController = NavController(LocalContext.current)
    val viewModel = PostViewModel()
    val postViewModel = PostListViewModel()
    val post = postViewModel.getPostList().first { post: Post -> post.getCommentsCount() != 0 }
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Black,
            )
        )
    }) { scaffoldPaddings ->
        PostContentLayout(
            navController = navController,
            postId = post.postId,
            modifier = Modifier.padding(scaffoldPaddings),
            viewModel = viewModel
        )
    }
}