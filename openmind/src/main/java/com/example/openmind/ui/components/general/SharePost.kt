package com.example.openmind.ui.components.general

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.openmind.R
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.spacing

@Composable
fun SharePost(postId: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, BorderLight, CircleShape),
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                /*TODO("open Share hamburger menu")*/
            },
            modifier = Modifier
                .padding(
                    vertical = 5.dp,
                    horizontal = MaterialTheme.spacing.small
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

