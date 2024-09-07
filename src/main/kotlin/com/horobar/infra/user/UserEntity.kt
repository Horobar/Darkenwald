package com.horobar.infra.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class UserEntity{
    @Id
    @GeneratedValue
    lateinit var  id: UUID

    @Column(unique = true)
    lateinit var keycloakId: UUID

    var name: String = "not provided"
}

