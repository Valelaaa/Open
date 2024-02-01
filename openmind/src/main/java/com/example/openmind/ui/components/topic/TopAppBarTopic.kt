package com.example.openmind.ui.components.topic

import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.repository.TopicRepositoryImpl
import com.example.openmind.data.viewModel.CurrentTopicViewModel
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

const val TAG: String = "TopicTopAppBar"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTopic(
    navController: NavController?,
    title: String = "",
    topicViewModel: CurrentTopicViewModel,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                Log.d(TAG, "Clicked Navigate Back")
                navController?.navigateUp()
            }) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back to article list",
                )
            }
        },
        actions = {
            Button(
                onClick = {
                    TopicRepositoryImpl().addNewTopic(topicViewModel.currentTopic.value)
                },
                modifier = Modifier
                    .defaultMinSize(minHeight = 22.dp, minWidth = 42.dp),
                shape = RoundedCornerShape(18.dp),
                enabled = topicViewModel.isButtonEnabled.value
            ) {
                Text(
                    stringResource(R.string.create_button),
                    color = LightText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.ManropeSemiBoldW600
                )
            }
            IconButton(
                onClick = {
                    Log.d(TAG, "Clicked 'More' Button")

                },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "more",
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(90f)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun TopAppBarArticlePreview() {
    OpenMindProjectTheme {
        Scaffold(topBar = {
            TopAppBarTopic(null, topicViewModel = viewModel())
        }, content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)){

            }
        })
    }
}
