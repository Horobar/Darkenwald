package com.horobar.infra.user

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.*

@ApplicationScoped
class UserService(
    private val userRepository: UserRepository,
    private val jwt: JsonWebToken,
) {

    fun getCurrentUser(): UserEntity {
        return findUserByExternalId(UUID.fromString(jwt.subject))
    }

    fun findUserByExternalId(externalId: UUID) = userRepository.findOrCreateIfNotExists(externalId)

    fun updateUser(user: UserDTO) = userRepository.update(user)
}