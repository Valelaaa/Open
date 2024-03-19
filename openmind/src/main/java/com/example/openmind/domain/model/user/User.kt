package com.example.openmind.domain.model.user

import com.example.openmind.R

class User(val nickname: String, val userPic: Int = R.drawable.user_pic){
    override fun toString(): String {
        return "User(nickname='$nickname', userPic=$userPic)"
    }
}
