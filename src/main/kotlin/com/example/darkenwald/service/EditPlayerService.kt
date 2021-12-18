package com.example.darkenwald.service

import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.models.PlayerDto
import com.example.darkenwald.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class EditPlayerService(
    private val playerRepository: PlayerRepository
) {
    /**
     * returns all Player Entities
     */
    fun getAllPlayerEntities(): List<PlayerEntity> = playerRepository.findAll()

    /**
     * Search the specific playerEntity and update its values
     * @param playerDto Received PlayerDTO from Website
     * @return updated and saved PlayerEntity
     */
    fun updatePlayer(playerDto: PlayerDto): PlayerEntity{
        val playerEntity = playerRepository.findByName(playerDto.name)
        if (playerEntity != null) {
            playerEntity.name  = playerDto.name
        playerEntity.avatarImageLink = playerDto.avatarImageLink
        if(playerDto.mail != null)
        playerEntity.mail = playerDto.mail
        if(playerDto.password != null)
        playerEntity.password = playerDto.password.toString()
        }
        return playerRepository.save(playerEntity as PlayerEntity)
    }
}