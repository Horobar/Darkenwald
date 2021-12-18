package com.example.darkenwald.service

import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val playerRepository: PlayerRepository
) {
    /**
     * check if the player with login name and password exist
     * @Param playerName
     * @Param password
     * @return if player exists return that PlayerEntity else null
     */
    fun verifyLogin(playerName: String, password: String ): PlayerEntity? {
       return playerRepository.findByNameAndPassword(playerName, password)
    }
}