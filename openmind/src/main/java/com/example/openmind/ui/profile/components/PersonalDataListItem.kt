package com.example.openmind.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.ui.components.general.borderBottom
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.LightThemeBackgroundColor
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.SteelBlue60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataListItem(
    painter: Painter,
    firstTitle: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    imageDescription: String? = null,
    isNavigationIconVisible: Boolean = false,
    isEditable: Boolean = false,
    editableValue: String? = null,
    onValueChange: ((String) -> Unit)? = null,
    editableFieldModifier: Modifier = Modifier,
    onSubmitEvent: (() -> Unit)? = null,
    shape: Shape? = null
) {
    Box {
        Row(
            modifier = modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .borderBottom((0.5).dp, BorderLight),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painter,
                    imageDescription, tint = MaibPrimary,
                    modifier = Modifier.padding(vertical = 18.dp)
                )
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = firstTitle, style = TextStyle(
                            fontFamily = FontFamily.ManropeSemiBoldW600,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    )
                    Text(
                        text = subTitle, style = TextStyle(
                            fontFamily = FontFamily.ManropeRegularW400,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = SteelBlue60
                        )
                    )
                }
            }
            if (isNavigationIconVisible) {
                Box {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(
                            id = R.string.read_more_label
                        ),
                        tint = SteelBlue60,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
        if (isEditable) {
            OutlinedTextField(
                value = editableValue ?: " ",
                onValueChange = onValueChange ?: {},
                modifier = editableFieldModifier.fillMaxWidth(),
                shape = shape ?: RoundedCornerShape(0),
                trailingIcon = {
                    IconButton(
                        onClick =
                        onSubmitEvent ?: {}
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = stringResource(id = R.string.create_button),
                            tint = MaibPrimary
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = LightThemeBackgroundColor
                ),
            )
        }
    }
}