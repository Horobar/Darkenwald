package com.horobar.campaign.character

import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PlayerCharService(private val playerCharRepository: PlayerCharRepository) {
    fun getCharactersForUser(userId: UUID): List<PlayerChar> {
        return playerCharRepository.find("user.id", userId).list()
    }
}
