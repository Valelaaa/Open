package com.example.openmind.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.R
import com.example.openmind.ui.components.general.BasicTopAppBar
import com.example.openmind.ui.profile.components.PersonalDataListItem
import com.example.openmind.ui.profile.components.PersonalHistoryListItem
import com.example.openmind.ui.screen.Screen
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkBlue40
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeSemiBoldW600
import com.example.openmind.ui.theme.spacing

@Composable
fun ProfileScreenView(
    viewModel: ProfileScreenViewModel,
    navController: NavController,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = MaterialTheme.spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .padding(MaterialTheme.spacing.small)
            ) {

                Image(
                    painterResource(id = R.drawable.user_image),
                    contentDescription = stringResource(R.string.user_profile_image),
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .border(
                        BorderStroke(2.dp, Color.White), shape = RoundedCornerShape(50)
                    )
                    .background(Color(0xFFD9F3EE))
                    .size(32.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painterResource(R.drawable.edit_icon), stringResource(R.string.edit_image),
                    tint = MaibPrimary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(16.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium, vertical = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.personal_data),
                style = TextStyle(
                    fontFamily = FontFamily.ManropeSemiBoldW600,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = DarkBlue40
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .border(BorderStroke(1.dp, BorderLight), shape = RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
            ) {
                PersonalDataListItem(
                    firstTitle = viewModel.getUserName(),
                    subTitle = viewModel.getUserNickname(),
                    painter = painterResource(id = R.drawable.user_pic),
                    imageDescription = stringResource(
                        id = R.string.user_picture
                    ),
                    isNavigationIconVisible = true,
                    isEditable = viewModel.getUserNameTextFieldViewState(),
                    editableValue = viewModel.getNicknameState().value,
                    onValueChange = viewModel.onNicknameChange(),
                    onSubmitEvent = viewModel.onRenameEvent(),
                    modifier = Modifier.clickable(onClick = { viewModel.setUserNameTextFieldVisible() }),
                    shape = RoundedCornerShape(
                        topStart = MaterialTheme.spacing.small,
                        topEnd = MaterialTheme.spacing.small
                    )
                )
                PersonalDataListItem(
                    painter = painterResource(id = R.drawable.mobile_icon),
                    firstTitle = viewModel.getUserPhoneNumber(),
                    subTitle = stringResource(R.string.phone_number),
                )
                PersonalDataListItem(
                    painter = painterResource(id = R.drawable.mail_icon),
                    firstTitle = viewModel.getUserEmail(),
                    subTitle = stringResource(
                        R.string.e_mail
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium, vertical = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.history),
                style = TextStyle(
                    fontFamily = FontFamily.ManropeSemiBoldW600,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = DarkBlue40
                ),
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
            )
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .border(BorderStroke(1.dp, BorderLight), shape = RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
            ) {
                PersonalHistoryListItem(
                    title = "Categories",
                    painter = painterResource(id = R.drawable.medal_icon),
                    imageDescription = stringResource(
                        id = R.string.user_picture
                    ),
                    isNavigationIconVisible = true,
                    modifier = Modifier.clickable(onClick = { navController.navigate(Screen.CategoriesScreen.route) })
                )
                PersonalHistoryListItem(
                    title = stringResource(R.string.liked_posts),
                    painter = painterResource(id = R.drawable.like_image),
                    imageDescription = stringResource(
                        id = R.string.user_picture
                    ),
                )
                PersonalHistoryListItem(
                    title = stringResource(R.string.your_comments),
                    painter = painterResource(id = R.drawable.comment_icon),
                    imageDescription = stringResource(
                        id = R.string.user_picture
                    ),
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreenPreview() {
    val viewModel = ProfileScreenViewModel()
    val navController = rememberNavController()
    Scaffold(topBar = {
        BasicTopAppBar(
            viewModel = viewModel,
            navController = navController,
            currentScreen = Screen.ProfileScreen
        )
    }) { paddingValues ->
        ProfileScreenView(
            viewModel = viewModel,
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}