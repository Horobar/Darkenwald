package com.example.darkenwald

import com.example.darkenwald.domains.ContentType
import com.example.darkenwald.domains.MessageEntity
import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.PlayerDto
import java.net.URL
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime

class TestData {
    companion object {
        fun generatePlayerEntities(n: Int): List<PlayerEntity> {
            val listOut = mutableListOf<PlayerEntity>()
            for (i in 0..n) {
                listOut.add(PlayerEntity().apply {
                    this.name = "name$i"
                    this.mail = "mail$i"
                    this.password = "password$i"
                    this.avatarImageLink = "link$i"
                })
            }
            return listOut
        }

        fun generateMessageEntity(n: Int): List<MessageEntity> {
            val listOut = mutableListOf<MessageEntity>()
            val players = generatePlayerEntities(n)
            for (i in 0..n) {
                listOut.add(MessageEntity().apply {
                    this.text = "text$i"
                    this.contentType = ContentType.PLAIN
                    this.created = Timestamp.valueOf(LocalDateTime.MIN)
                    this.playerEntity = players[i]
                })
            }
            return listOut
        }

        fun generateMessageDTO(n: Int): List<MessageDto>{
            val listOut = mutableListOf<MessageDto>()
            for (i in 0..n) {
                listOut.add(MessageDto(
                    "text$i",
                    LocalDateTime.MIN,
                ))
            }
            return listOut
        }

        fun generatePlayerDTO(n: Int): List<PlayerDto>{
            val listOut = mutableListOf<PlayerDto>()
            for (i in 0..n) {
                listOut.add(PlayerDto(
                    "name$i",
                    "assets/avatars/default_avatar.png",
                    "password$i",
                    "password$i",
                    "mail$i"
                ))
            }
            return listOut
        }
    }

}