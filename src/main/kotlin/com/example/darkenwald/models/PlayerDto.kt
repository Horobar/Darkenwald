package com.example.darkenwald.models

import java.net.URL

data class PlayerDto(
    val name: String,
    val avatarImageLink: URL?,
    val password: String,
    val password_confirmation: String?,
    val mail: String?
)