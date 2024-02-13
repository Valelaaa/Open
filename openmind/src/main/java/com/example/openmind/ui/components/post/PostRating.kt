package com.example.openmind.ui.components.post

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.ui.components.general.borderRight
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.MaibError
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700

@Composable
fun PostRating(postId: String, rating: Int, modifier: Modifier = Modifier) {

    val mutableRating by remember { mutableIntStateOf(rating) }

    var liked by remember { mutableStateOf(false) }
    var disliked by remember { mutableStateOf(false) }
    val likeColor = if (liked) MaibPrimary else DarkBlue40
    val dislikeColor = if (disliked) MaibError else DarkBlue40


    val actionColor = if (liked) MaibPrimary else if (disliked) MaibError else DarkBlue40
    val strokeActionColor = if (liked) MaibPrimary else if (disliked) MaibError else BorderLight

    val currentRating =
        if (liked) mutableRating + 1 else if (disliked) mutableRating - 1 else mutableRating

    Row(
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, strokeActionColor, CircleShape)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                /*TODO(post rating increases - patch request,button color - maib primary)*/
                liked = !liked
                disliked = false
            },
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 4.dp, end = 1.dp)
                .size(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = stringResource(R.string.contentdescription_increase),
                tint = likeColor
            )
        }
        Box(Modifier.padding(end = 6.dp)) {
            Text(
                text = "$currentRating",
                fontFamily = FontFamily.ManropeBoldW700,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = actionColor,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .defaultMinSize(minWidth = 42.dp)
                    .borderRight(0.5.dp, BorderLight)
            )
        }
        IconButton(
            onClick = { /*TODO(post rating decreases - patch request, button color ~ red)*/
                disliked = !disliked
                liked = false
            },
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 1.dp, end = 4.dp)
                .rotate(180f)
                .size(20.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = stringResource(R.string.contentdescription_decrease),
                tint = dislikeColor
            )
        }
    }
}

@Preview
@Composable
fun RatingPreview() {
    val mockPost = PostRepositoryProvider.provideRepository().getMockPost()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 35.dp, top = 35.dp)
    ) {
        PostRating(
            postId = mockPost.postId,
            rating = mockPost.rating,
            modifier = Modifier.background(
                Color.White
            )
        )
    }

}