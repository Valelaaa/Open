package com.example.openmind.ui.success_post_register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.R
import com.example.openmind.ui.theme.ManropeExtraBoldW800
import com.example.openmind.ui.theme.ManropeRegularW400
import com.example.openmind.ui.theme.NightBlue
import com.example.openmind.ui.theme.spacing

@Composable
fun SuccessRegistrationPostView(
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.success_post_create_header),
                contentDescription = "header",
                contentScale = ContentScale.FillWidth,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.success_create_post),
                style = TextStyle(
                    fontFamily = FontFamily.ManropeExtraBoldW800,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    letterSpacing = (-0.5).sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            )
            Text(
                text = stringResource(R.string.create_post_info),
                style = TextStyle(
                    fontFamily = FontFamily.ManropeRegularW400,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            Button(
                onClick = {
                    navController.navigateUp()
                    navController.navigateUp()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.large,
                        end = MaterialTheme.spacing.large,
                        top = 16.dp
                    ),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = NightBlue
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "OK", style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
    BackHandler(
        enabled = true
    ) {
        navController.navigateUp()
        navController.navigateUp()
    }
}


@Preview(
    backgroundColor = 0xFFF7F8FA,
    showBackground = true
)
@Composable
fun PreviewSuccessRegistrationPost() {
    SuccessRegistrationPostView(
        navController = rememberNavController(),
    )
}