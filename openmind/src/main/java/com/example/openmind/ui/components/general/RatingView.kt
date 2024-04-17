package com.example.openmind.ui.components.general

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.domain.model.rating.RatingInfo
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.MaibError
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700

@Composable
fun RatingView(
    rating: RatingInfo,
    modifier: Modifier = Modifier,
    isComment: Boolean = false,
    onRatingChange: (String, Int) -> Unit
) {
    var mutableRating by remember { rating.rating }
    var rated by remember {// 0 - not rated, 1 - liked, -1 - disliked
        rating.isRated
    }
    val likeColor = if (rated == 1) MaibPrimary else DarkBlue40
    val dislikeColor = if (rated == -1) MaibError else DarkBlue40

    val actionColor = if (rated == 1) MaibPrimary else if (rated == -1) MaibError else DarkBlue40
    var strokeActionColor =
        if (rated == 1) MaibPrimary else if (rated == -1) MaibError else BorderLight


    var strokeColor = BorderLight

    if (isComment) {
        strokeColor = Color.Transparent
        strokeActionColor = Color.Transparent
    }
    Row(
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, strokeActionColor, CircleShape)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
//                /*TODO(post rating increases - patch request,button color - maib primary)*/
                val newRating = when (rated) {
                    1 -> mutableRating - 1
                    -1 -> mutableRating + 2
                    else -> mutableRating + 1
                }
                mutableRating = newRating
                rated = if (rated == 1) 0 else 1
                onRatingChange(rating.ratingId, 1)

            },
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 4.dp, end = 1.dp)
                .size(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = stringResource(R.string.content_description_increase),
                tint = likeColor
            )
        }
        Box(Modifier.padding(end = 6.dp)) {
            Text(
                text = "$mutableRating",
                fontFamily = FontFamily.ManropeBoldW700,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = actionColor,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .defaultMinSize(minWidth = 42.dp)
                    .borderRight(0.5.dp, strokeColor)
            )
        }
        IconButton(
            onClick = { /*TODO(post rating decreases - patch request, button color ~ red)*/
                val newRating = when (rated) {
                    -1 -> mutableRating + 1
                    1 -> mutableRating - 2
                    else -> mutableRating - 1
                }
                mutableRating = newRating
                rated = if (rated == -1) 0 else -1
                onRatingChange(rating.ratingId, -1)
            },
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 1.dp, end = 4.dp)
                .rotate(180f)
                .size(20.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = stringResource(R.string.content_description_decrease),
                tint = dislikeColor
            )
        }
    }
}

