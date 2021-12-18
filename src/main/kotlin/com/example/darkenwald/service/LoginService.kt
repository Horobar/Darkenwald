package com.example.darkenwald.service

import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val playerRepository: PlayerRepository
) {
    fun verifyLogin(playerName: String, password: String ): PlayerEntity? {
       return playerRepository.findByNameAndPassword(playerName, password)
    }
}