package com.example.openmind.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.openmind.R
import com.example.openmind.domain.model.post.Post
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.MaibPrimary

@Composable
fun SharePost(postId: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
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
                tint = MaibPrimary
            )
        }
    }
}

@Preview
@Composable
fun SharePostPreview() {
    val mockPost = Post(title = "Title")
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(35.dp)
    ) {
        SharePost(postId = mockPost.postId, modifier = Modifier.background(Color.White))
    }
}