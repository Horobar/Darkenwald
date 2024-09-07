package com.horobar.library.article

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob
import java.util.*

@Entity
class Article {

    @Id
    @GeneratedValue
    var id: UUID? = null
    var title: String? = null
    @Lob
    var content: String? = null
}
