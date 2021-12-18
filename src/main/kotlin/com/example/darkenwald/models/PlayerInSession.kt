package com.example.darkenwald.models

import java.io.Serializable

data class PlayerInSession (
    val name: String,
    val avatarImageLink: String,
    val isAdmin: Boolean
): Serializable
