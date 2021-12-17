package com.example.darkenwald.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PLAYER")
class PlayerEntity {
    @Id
    @Column(name = "player_id", nullable = false)
    open var id: Long? = null

    @Column(name = "NAME")
    lateinit var name: String

    @Column(name = "PASSWORD", nullable = false)
    lateinit var password: ContentType

    @Column(name = "MAIL", nullable = false)
    lateinit var mail: String

    @Column(name = "AVATAR_IMAGE_LINK", nullable = false)
    lateinit var avatarImageLink: String
}