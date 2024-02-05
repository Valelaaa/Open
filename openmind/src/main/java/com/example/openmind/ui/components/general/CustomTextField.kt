package com.example.openmind.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.ManropeSemiBoldW600

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
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
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
    maxLines: Int = 1,
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
            ),
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
                innerTextField =
                innerTextField,
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomTextFieldPreview() {
    var commentMessage = remember {
        ""
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(35.dp)
    ) {
        Column(Modifier.background(Color.Black)) {
            CustomTextField(
                value = commentMessage,
                onValueChange = { commentMessage = it },
                shape = RoundedCornerShape(6.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    disabledIndicatorColor = Color.Transparent
//                ),
                placeholder = {
                    Text(
//                                text = stringResource(R.string.comment_placeholder),
                        text = "Comments...",
                        color = IconColor,
                        fontFamily = FontFamily.ManropeSemiBoldW600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    )
                },
                modifier = Modifier
                    .defaultMinSize(minHeight = 40.dp)
                    .padding(start = 14.dp, top = 0.dp, bottom = 0.dp),

                )
        }
    }
}
