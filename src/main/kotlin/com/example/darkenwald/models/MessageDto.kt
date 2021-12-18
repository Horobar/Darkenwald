package com.example.darkenwald.models

import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

data class MessageDto(
    var text: String,
    var created: LocalDateTime?,
)
