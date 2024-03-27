package com.example.openmind.ui.create_post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.R
import com.example.openmind.ui.create_post.viewModel.CreatePostViewModel
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.ButtonBorder
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeBoldW700
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.NightBlue
import com.example.openmind.ui.theme.SteelBlue60
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostContentView(
    navController: NavController,
    viewModel: CreatePostViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 30.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)) {
        Column {
            Row {
                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = SteelBlue60,
                    ),
                    border = BorderStroke(1.dp, ButtonBorder)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Choose a Category",
                            style = TextStyle(
                                color = SteelBlue60,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily.ManropeSemiBoldW600
                            ),
                            textAlign = TextAlign.Start
                        )
                        Box(Modifier.padding(end = 16.dp)) {
                            Icon(
                                Icons.Default.KeyboardArrowRight,
                                contentDescription = "choose",
                                tint = SteelBlue60,
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = "info",
                        modifier = Modifier
                            .size(20.dp),
                        tint = MaibPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.getTitle(),
                onValueChange = viewModel.onTitleChange(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    unfocusedBorderColor = ButtonBorder
                ),
                placeholder = {
                    Text(
                        "Title", style = TextStyle(
                            color = SteelBlue60,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily.ManropeSemiBoldW600
                        )
                    )
                },
                textStyle = TextStyle(
                    color = NightBlue,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily.ManropeSemiBoldW600
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .padding(end = 30.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = viewModel.getDescription(),
                onValueChange = viewModel.onDescriptionChange(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    unfocusedBorderColor = ButtonBorder

                ),
                placeholder = {
                    Text(
                        "Description", style = TextStyle(
                            color = SteelBlue60,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily.ManropeSemiBoldW600
                        )
                    )
                },
                textStyle = TextStyle(
                    color = NightBlue,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily.ManropeSemiBoldW600
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .defaultMinSize(minHeight = 100.dp)
                    .fillMaxWidth()
                    .padding(end = 30.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.createPost()
                    navController.navigate(Screen.SuccessRegisteredPostScreen.route)
                })

            )


        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                viewModel.onCreatePostButton()
                navController.navigate(Screen.SuccessRegisteredPostScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaibPrimary
            ), modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .padding(start = 10.dp, end = 20.dp),
            contentPadding = PaddingValues(vertical = 13.dp)
        ) {
            Text(
                "Create", style = TextStyle(
                    fontFamily = FontFamily.ManropeBoldW700,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF7F8FA
)
@Composable
fun CreatePostContentPreview() {
    val navController: NavController = rememberNavController()
    val viewModel = CreatePostViewModel()
    OpenMindProjectTheme {

        CreatePostContentView(
            navController,
            viewModel = viewModel,
        )
    }
}