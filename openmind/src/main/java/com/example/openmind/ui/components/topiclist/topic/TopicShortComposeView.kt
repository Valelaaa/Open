package com.example.openmind.ui.components.topiclist.topic

import NoRippleInteractionSource
import android.content.Context
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
import com.example.openmind.R
import com.example.openmind.data.topic.Topic
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.example.openmind.ui.components.general.bottomBorder
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimetr
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60

@Composable
fun TopicShortComposeView(
    topic: Topic, category: String, modifier: Modifier = Modifier,
    context: Context? = null
) {
    Column(
        modifier
            .fillMaxWidth()
            .bottomBorder(1.dp, Delimetr)
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
                        .padding(start = 0.dp, end = 8.dp)
                )
                //Category name
                Text(
                    text = category,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    maxLines = 1,
                    fontFamily = FontFamily.ManropeSemiBoldW600,
                )

                //Created Time
                Text(
                    text = topic.formatElapsedTime(),
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
                },
                modifier = Modifier
                    .size(24.dp),
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

        // Topic Content
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable(onClick = {
                    /*TODO(NavigateToTopic)*/
                })
        ) {
            Row {
                //Topic Title
                Text(
                    text = topic.title.trimIndent(), fontSize = 14.sp,
                    fontFamily = FontFamily.ManropeBoldW700,
                    color = DarkBlue40,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = (Modifier
                        .weight(1f))
                )

                IconButton(onClick = { /*TODO("Just IconButton Style")*/ }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.contentdescription_more),
                        tint = SteelBlue60,
                    )
                }

            }
            Text(
                text = stringResource(R.string.read_more_label),
                fontSize = 14.sp,
                fontFamily = FontFamily.ManropeRegularW400,
                color = SteelBlue60,
            )
        }
        // FeedBack and Share
        Column {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
            ) {
                //Rating
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, BorderLight, CircleShape)
                        .padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        IconButton(
                            onClick = { /*TODO(topic rating increases - patch request,button color - maib primary)*/ },
                            modifier = Modifier
                                .padding(5.dp)
                                .size(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_up),
                                contentDescription = stringResource(R.string.contentdescription_increase),
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "${topic.rating}",
                            fontFamily = FontFamily.ManropeBoldW700,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = DarkBlue40,
                            maxLines = 1
                        )
                    }
                    Column {
                        IconButton(
                            onClick = { /*TODO(topic rating decreases - patch request, button color ~ red)*/ },
                            modifier = Modifier
                                .padding(5.dp)
                                .size(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_up),
                                contentDescription = stringResource(R.string.contentdescription_decrease),
                                modifier = Modifier
                                    .rotate(180f)
                            )
                        }
                    }
                }
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
                            .padding(end = 30.dp)
                            .clickable(onClick = {
                                /*TODO("Navigate to Topic -> scrollTo comments")*/
                            }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /*TODO(import styles, use box )*/ },
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    top = 5.dp,
                                    bottom = 5.dp,
                                    end = 6.dp
                                )
                                .size(20.dp)

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = stringResource(id = R.string.contentdescription_decrease),
                            )
                        }
                        Text(
                            text = stringResource(
                                R.string.comments_count,
                                topic.getCommentsCount()
                            ),
                            fontFamily = FontFamily.ManropeBoldW700,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, BorderLight, CircleShape),
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { /*TODO("open Share hamburger menu")*/ },
                        modifier = Modifier
                            .padding(
                                vertical = 5.dp,
                                horizontal = 8.dp
                            )
                            .size(20.dp)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.export),
                            contentDescription = "export",
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun TopicShortComposeViewPreview() {
    val topic = CurrentTopicViewModel().getTopic()
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        TopicShortComposeView(
            topic = topic,
            category = Categories.BUG.getStringValue(),
            modifier = Modifier
                .padding(horizontal = 28.dp, vertical = 30.dp)
                .background(LightText),
            LocalContext.current
        )
    }
}