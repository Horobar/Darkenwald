package com.example.darkenwald

import com.example.darkenwald.domains.ContentType
import com.example.darkenwald.repository.MessageRepository
import com.example.darkenwald.repository.PlayerRepository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [
    RepositoryTestConfig::class
])
internal class ExtensionsKtTest(
    private val playerRepository: PlayerRepository,
    private val messageRepository: MessageRepository
){
    @BeforeEach
    fun setup(){
        playerRepository.deleteAll()
        messageRepository.deleteAll()
    }

    @Test
    fun `MessageDto asMessageEntity maps correctly`(){
        val player = TestData.generatePlayerEntities(1)
        val savedPlayer = playerRepository.save(player.first())

        var message = TestData.generateMessageDTO(1).first()

        var messageEntity = message.asMessageEntity(ContentType.PLAIN)




    }


}