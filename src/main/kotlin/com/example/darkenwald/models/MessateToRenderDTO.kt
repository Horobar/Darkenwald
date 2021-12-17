package com.example.darkenwald.models

import java.net.URL
import java.time.Instant
import java.time.LocalDateTime

data class MessageToRenderDTO(
    val text: String,
    val created: LocalDateTime,
    val updated: LocalDateTime? = null,
    val username: String,
    val avatarImageLink: String
)
