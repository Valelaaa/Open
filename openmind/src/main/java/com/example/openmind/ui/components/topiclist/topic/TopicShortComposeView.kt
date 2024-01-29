package com.example.openmind.ui.components.topiclist.topic

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.data.topic.Topic
import com.example.openmind.data.viewModel.Categories
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.example.openmind.ui.components.general.bottomBorder
import com.example.openmind.ui.components.topic.TAG
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.Delimetr
import com.example.openmind.ui.theme.LightGray80
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
                Box(modifier = Modifier.padding(start = 0.dp, end = 8.dp)) {
                    Image(
                        painter = painterResource(R.drawable.user_pic),
                        contentDescription = "user_icon",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Box {
                    //Category name
                    Text(
                        text = "c/$category",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        fontFamily = FontFamily.ManropeSemiBoldW600,

                        )
                }
                //Loaded/Created Time
                Box {
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
            }

            Row() {
                IconButton(
                    onClick = {
                        Log.d(TAG, "Clicked 'More' Button")
                    },
                    modifier = Modifier
                        .size(24.dp),
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "more",
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(90f)
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(top = 12.dp, start = 1.dp)) {
            Row() {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = topic.title, fontSize = 14.sp,
                        fontFamily = FontFamily.ManropeBoldW700,
                        color = DarkBlue40
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "more",
                            tint = SteelBlue60
                        )
                    }
                }

            }
            Row() {
                Text(
                    text = "Read more...",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.ManropeRegularW400,
                    color = SteelBlue60,
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(modifier = Modifier.padding(end = 4.dp)) {
                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, BorderLight, CircleShape)
                                .padding(0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                IconButton(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(20.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_up),
                                        contentDescription = "increase",
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
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(20.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_up),
                                        contentDescription = "decrease",
                                        modifier = Modifier
                                            .rotate(180f)
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.padding(end = 4.dp)) {

                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, BorderLight, CircleShape)
                                .padding(end = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                IconButton(
                                    onClick = { /*TODO*/ },
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
                                        contentDescription = "decrease",
                                    )
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${topic.getCommentsCount()} Comments",
                                    fontFamily = FontFamily.ManropeBoldW700,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, BorderLight, CircleShape),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        IconButton(
                            onClick = { /*TODO*/ },
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