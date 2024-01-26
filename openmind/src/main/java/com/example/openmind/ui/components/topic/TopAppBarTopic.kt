package com.example.openmind.ui.components.topic

import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.data.viewModel.TopicViewModel
import com.example.openmind.ui.theme.LightText
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

const val TAG: String = "TopicTopAppBar"

@Composable
fun TopAppBarArticle(toolbar: android.widget.Toolbar) {
    AndroidView(
        factory = {
            toolbar
        }
    )
}

@Composable
fun TopAppBarArticle(toolbar: Toolbar) {
    AndroidView(
        factory = {
            toolbar
        }
    )
}

@Composable
fun TopAppBarArticle(topBar: @Composable () -> Unit) {
    topBar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarArticle(
    navController: NavController?,
    title: String = "",
    topicViewModel: TopicViewModel,
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
//                          contentNavigator.validate(topicViewModel.)
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
                    fontFamily = ManropeSemiBoldW600
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
fun TopAppBarArticleToolBarPreview() {
    val context = LocalContext.current
    val toolbar = remember {
        Toolbar(context)
    }
    toolbar.apply {
        title = "Title"
        setTitleTextColor(0xFFFFC0CB.toInt())
        setNavigationIcon(R.drawable.ic_launcher_foreground)
        setBackgroundColor(0xFF000000.toInt())
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        this.layoutParams = layoutParams
        left = 0
        top = 0
        foregroundGravity = 1
    }
    Scaffold(topBar = {
        TopAppBarArticle(toolbar)
    }, content = { paddingValues ->
        Text(
            text = "Text",
            modifier = Modifier.padding(paddingValues)
        )
    })
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun TopAppBarArticlePreview() {
    OpenMindProjectTheme {
        Scaffold(topBar = {
            TopAppBarArticle(null, topicViewModel = viewModel())
        }, content = { paddingValues ->
            Text(
                text = "Text",
                modifier = Modifier.padding(paddingValues)
            )
        })
    }
}
