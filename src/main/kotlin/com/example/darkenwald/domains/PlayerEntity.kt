package com.example.darkenwald.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PLAYER")
class PlayerEntity {
    @Id
    @GeneratedValue
    @Column(name = "player_id", nullable = false)
    var id: Long? = null

    @Column(name = "NAME")
    lateinit var name: String

    @Column(name = "PASSWORD", nullable = false)
    lateinit var password: String

    @Column(name = "MAIL", nullable = false)
    lateinit var mail: String

    @Column(name = "AVATAR_IMAGE_LINK", nullable = false)
    var avatarImageLink: String = "assets/avatars/default_avatar.png"

    @Column(name = "IS_ADMIN", nullable = false)
    var isAdmin: Boolean = false
}