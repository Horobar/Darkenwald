package com.example.darkenwald.models

import java.time.LocalDateTime

data class MessageToRenderDTO(
    val text: String,
    val created: LocalDateTime,
    val updated: LocalDateTime? = null,
    val username: String,
    val avatarImageLink: String = "static/assets/avatars/default_avatar.png"
)
