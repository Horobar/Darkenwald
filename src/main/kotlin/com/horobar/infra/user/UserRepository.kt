package com.horobar.infra.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.util.*

@ApplicationScoped
class UserRepository : PanacheRepositoryBase<UserEntity, UUID> {

    @Transactional
    fun findOrCreateIfNotExists(externalId: UUID): UserEntity {
        return find("keycloakId", externalId)
            .firstResult() ?: create(externalId)
    }

    @Transactional
    fun update(user: UserDTO): UserEntity {
        return find("keycloakId", user.userId)
            .firstResult()
            ?.apply {
                user.name?.let { name = it }
                persist(this)
            } ?: throw IllegalArgumentException("User not found")
    }

    @Transactional
    fun create(keycloakId: UUID): UserEntity {
        val entity = UserEntity().apply {
            this.keycloakId = keycloakId
            name = "not provided"
        }

        persist(entity)
        return entity
    }
}