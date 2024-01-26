package com.example.openmind.ui.components.topic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.openmind.data.viewModel.TopicViewModel
import com.example.openmind.ui.components.general.CustomTextField
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.ManropeRegularW400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicBodyTextField(
    topicViewModel: TopicViewModel,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val topicDescriptionSize = 16.sp
    CustomTextField(
        value = topicViewModel.description.value,
        onValueChange = { inputString ->
            topicViewModel.updateDescription(inputString)
        },
        placeholder = {
            Text(
                text = stringResource(R.string.create_topic_body_text),
                fontSize = topicDescriptionSize,
                fontWeight = FontWeight.W400,
                color = DarkGray20,
                fontFamily = ManropeRegularW400
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
            fontSize = topicDescriptionSize,
            fontWeight = FontWeight.W400,
            fontFamily = ManropeRegularW400,
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