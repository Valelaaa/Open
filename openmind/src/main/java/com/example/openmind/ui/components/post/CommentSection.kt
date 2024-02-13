package com.example.openmind.ui.components.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommentsSection() {
    val mockComments = listOf("Comment1", "Comment2", "Comment3")
    LazyColumn(content = {
        items(items = mockComments) { item ->
            Text(text = item)
        }
    }, modifier = Modifier.background(Color.White))
}

@Preview
@Composable
fun CommentsSectionPreview() {
    Box(
        Modifier
            .padding(35.dp)
            .background(Color.Black)
    ) {
        CommentsSection()
    }
}