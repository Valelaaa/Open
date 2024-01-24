package com.example.openmind.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.openmind.ui.theme.manropeRegularW400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicBodyTextField(text: String, modifier: Modifier = Modifier.fillMaxSize()) {
    var mutableText by remember {
        mutableStateOf(text)
    }
    val textFiledFontSize = 16.sp
    CustomTextField(
        value = mutableText,
        onValueChange = {
            mutableText = it
        },
        placeholder = {
            Text(
                text = stringResource(R.string.create_article_body_text),
                fontSize = textFiledFontSize,
                fontWeight = FontWeight.W400,
                color = TextInputColor,
                fontFamily = manropeRegularW400
            )
        },
        contentPadding = PaddingValues(
            top = 5.dp,
            start = 15.dp,
            end = 15.dp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
            fontSize = textFiledFontSize,
            fontWeight = FontWeight.W400,
            fontFamily = manropeRegularW400,
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