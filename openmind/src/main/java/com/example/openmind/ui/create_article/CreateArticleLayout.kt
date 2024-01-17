package com.example.openmind.ui.create_article

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.takeOrElse
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
import com.example.openmind.ui.theme.BackgroundLight
import com.example.openmind.ui.theme.InputLabelTextColor

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
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CustomTextField(
            value = titleText,
            onValueChange = {
                titleText = it
                println(titleText)
            },

            colors = TextFieldDefaults.textFieldColors(
                textColor = InputLabelTextColor,
                containerColor = BackgroundLight,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                ),
            placeholder = {
                Text(
                    text = stringResource(R.string.create_article_title),
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    color = InputLabelTextColor,
                    fontWeight = FontWeight.W800
                )
            },
            contentPadding = PaddingValues(
                start = 30.dp,
                top = 10.dp,
                bottom = 10.dp,
                end = 30.dp
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        CustomTextField(
            value = articleDescription,
            onValueChange = {
                articleDescription = it
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.create_article_body_text),
                    fontSize = 12.sp,
                    color = InputLabelTextColor,
                    fontWeight = FontWeight.W400,
                    lineHeight = 30.sp,
                )
            },
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = InputLabelTextColor,
                fontWeight = FontWeight.W400,
                lineHeight = 30.sp,
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = InputLabelTextColor,
                containerColor = BackgroundLight,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            contentPadding = PaddingValues(
                start = 30.dp,
                top = 0.dp,
                bottom = 10.dp,
                end = 30.dp
            )

        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = LocalTextStyle.current,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    placeholder: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    shape: Shape = TextFieldDefaults.outlinedShape,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            )
            .fillMaxSize(),
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardActions = keyboardActions,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = {
                    Text(
                        text = value,
                        fontSize = textStyle.fontSize,
                        color = textStyle.color,
                        fontWeight = textStyle.fontWeight,
                        lineHeight = textStyle.lineHeight
                    )
                },
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                colors = colors,
                shape = shape,
                isError = isError,
                placeholder = placeholder,
                contentPadding = contentPadding,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                supportingText = supportingText,
            )
        }
    )
}

@Preview
@Composable
fun PreviewCreateArticleView() {
    CreateArticleLayout(navController = null)
}