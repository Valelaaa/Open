package com.example.openmind.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.ui.theme.TextInputColor
import com.example.openmind.ui.theme.manropeExtraBoldW800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicTitleTextField(titleText: String, modifier: Modifier = Modifier) {
    var mutableTitleText by remember {
        mutableStateOf(titleText)
    }
    val titleMaxSize = 300
    CustomTextField(
        value = mutableTitleText,
        onValueChange = {
            mutableTitleText = it.dropLast(it.length - titleMaxSize)
        },
        placeholder = {
            Text(
                text = stringResource(R.string.create_article_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.W800,
                color = TextInputColor,
                lineHeight = 30.sp,
                fontFamily = manropeExtraBoldW800
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
            fontFamily = manropeExtraBoldW800,
            lineHeight = 30.sp,
            color = TextInputColor,
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