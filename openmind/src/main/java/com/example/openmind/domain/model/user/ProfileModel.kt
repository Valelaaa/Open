package com.example.openmind.domain.model.user

data class ProfileModel(
    val firstName: String,

    val lastName: String,

    var nickname: String,

    val phoneNumber: String,

    val email: String,
)
