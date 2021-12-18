package com.example.darkenwald.service

import com.example.darkenwald.asPlayerEntity
import com.example.darkenwald.models.PlayerDto
import com.example.darkenwald.repository.PlayerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import javax.transaction.Transactional


@Service
class RegistrationService(
    private val playerRepository: PlayerRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(RegistrationService::class.java)

    fun verifyRegistration(player: PlayerDto): List<String> {
        val warnings = mutableListOf<String>()
        val foundPlayer = playerRepository.findByName(player.name)

        if(player.password != player.password_confirmation){
            warnings.add("The passwords are not the same!")
        }

        if(foundPlayer != null){
            warnings.add("A player with that name already exists!")
        }

        if(warnings.isEmpty()) {
            saveRegistration(player)
        }
        return warnings
    }

    @Transactional
    fun saveRegistration(playerDto: PlayerDto){
        playerRepository.save(playerDto.asPlayerEntity())
        logger.info("${OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm"))} -- ${playerDto.name} is now registered")
    }
}