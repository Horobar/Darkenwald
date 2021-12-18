package com.example.darkenwald.models

data class PlayerDto(
    val name: String,
    val avatarImageLink: String = "static/avatars/default_avatar.png",
    val password: String? = null,
    val password_confirmation: String? = null,
    val mail: String? = null
)