package com.example.openmind.ui.profile

import androidx.compose.runtime.MutableState
import com.example.openmind.ui.GlobalViewModel

class ProfileScreenViewModel : GlobalViewModel() {
    private val viewState = ProfileScreenViewState()


    fun getUserName() = "${viewState.profile.firstName} ${viewState.profile.lastName}"
    fun getUserNickname() = "@${viewState.profile.nickname}"
    fun getUserPhoneNumber() = "+373 ${viewState.profile.phoneNumber}"
    fun getUserEmail() = viewState.profile.email
    fun getNicknameState(): MutableState<String> = viewState.nicknameState
    fun onNicknameChange(): ((String) -> Unit) {
        return { inputValue ->
            viewState.nicknameState.value = inputValue
        }
    }

    fun onRenameEvent(): () -> Unit = {
        setUserNameTextFieldHidden()
        viewState.profile.nickname = viewState.nicknameState.value
    }


    fun getUserNameTextFieldViewState() = viewState.isEditNickNameFieldVisible.value

    fun setUserNameTextFieldVisible() {
        viewState.isEditNickNameFieldVisible.value = true
    }

    fun setUserNameTextFieldHidden() {
        viewState.isEditNickNameFieldVisible.value = false
    }

}
