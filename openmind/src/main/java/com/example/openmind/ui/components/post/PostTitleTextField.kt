package com.example.openmind.ui.components.post

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.data.viewModel.createpost.CreatePostViewModel
import com.example.openmind.ui.components.general.CustomTextField
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeExtraBoldW800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTitleTextField(
    viewModel: CreatePostViewModel,
    modifier: Modifier = Modifier
) {
    val titleMaxSize = 300

    val postTitle = remember {
        viewModel.title
    }
    CustomTextField(
        value = postTitle.value,
        onValueChange = { inputString ->
            postTitle.value = inputString.take(titleMaxSize)
            viewModel.updateTitle(postTitle.value)
        },
        keyboardActions = KeyboardActions(
            onNext = {

            }
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.create_post_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.W800,
                color = DarkGray20,
                lineHeight = 30.sp,
                fontFamily = FontFamily.ManropeExtraBoldW800
            )
        },
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 10.dp,
            end = 15.dp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W800,
            fontFamily = FontFamily.ManropeExtraBoldW800,
            lineHeight = 30.sp,
            color = DarkGray20,
            textAlign = TextAlign.Justify
        ),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
    )
}