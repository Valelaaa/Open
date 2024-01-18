package com.example.openmind.ui.create_article

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openmind.R
import com.example.openmind.ui.components.CustomTextField
import com.example.openmind.ui.theme.BackgroundLight
import com.example.openmind.ui.theme.PlaceHolderInputColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateArticleLayout(navController: NavController?, modifier: Modifier = Modifier) {
    var titleText by remember {
        mutableStateOf("")
    }
    var articleDescription by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomTextField(
                value = titleText,
                onValueChange = {
                    titleText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_article_title),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W800,
                        color = PlaceHolderInputColor,
                        lineHeight = 30.sp
                    )
                },
                contentPadding = PaddingValues(
                    start = 30.dp,
                    top = 10.dp,
                    end = 30.dp,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 30.sp,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = BackgroundLight
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize().background(Color.Green)
        ) {
            CustomTextField(
                value = articleDescription,
                onValueChange = {
                    articleDescription = it
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_article_body_text),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400, color = PlaceHolderInputColor,
                    )
                },
                contentPadding = PaddingValues(
                    top = 5.dp,
                    start = 30.dp,
                    end = 30.dp,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = BackgroundLight
                )
            )
        }
    }
}



@Preview
@Composable
fun PreviewCreateArticleView() {
    CreateArticleLayout(navController = null)
}

@Preview
@Composable
fun PreviewCustomInput() {
    var remembered by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTextField(
            value = remembered,
            onValueChange = {
                remembered = it
            },
            placeholder = { Text("Title... ", fontSize = 24.sp) },
            contentPadding = PaddingValues(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 30.sp,
            ),
        )
    }
}