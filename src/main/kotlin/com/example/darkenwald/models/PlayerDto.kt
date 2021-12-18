package com.example.darkenwald.models

import java.net.URL

data class PlayerDto(
    val name: String,
    val avatarImageLink: String = "assets/avatars/default_avatar.png",
    val password: String? = null,
    val password_confirmation: String? = null,
    val mail: String? = null
)