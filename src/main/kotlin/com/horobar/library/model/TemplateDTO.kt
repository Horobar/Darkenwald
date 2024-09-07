package com.horobar.library.model

import java.util.*

data class Category(
    val name: String,
    val links: List<Link>
)

data class Link(
    val id: UUID,
    val name: String,
    val path: String
)