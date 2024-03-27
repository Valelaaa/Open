package com.example.openmind.ui.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.openmind.domain.model.user.ProfileModel

class ProfileScreenViewState {

    val profile = ProfileModel(
        firstName = "John",
        lastName = "Doe",
        nickname = "johndoe",
        phoneNumber = "675-474-54",
        email = "MyPersonalEmail@gmail.com"
    )


    val isEditNickNameFieldVisible: MutableState<Boolean> = mutableStateOf(false)
    val nicknameState: MutableState<String> = mutableStateOf(profile.nickname)
}
