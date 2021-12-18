package com.example.darkenwald.service

import com.example.darkenwald.asMessageEntity
import com.example.darkenwald.mapToViewModel
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import com.example.darkenwald.repository.MessageRepository
import com.example.darkenwald.repository.PlayerRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class MessageServiceImpl(
    val messageRepository: MessageRepository,
    val playerRepository: PlayerRepository,
) : MessageService{

    private var startTime: LocalDateTime = LocalDateTime.now().minusWeeks(1)

    @EventListener
    fun getStartTime(event: DayBreakEvent){
        startTime = event.dateTime
    }

    @Transactional
    override fun fromDayStart(): List<MessageToRenderDTO> {
        return messageRepository.findAllByCreatedAfterOrderByCreatedAsc(Timestamp.valueOf(startTime)).mapToViewModel()
    }
    @Transactional
    override fun post(message: MessageDto, playerName: String): List<MessageToRenderDTO> {
        val player = playerRepository.findByName(playerName)
        val messageNew = message.asMessageEntity()
        messageNew.playerEntity = player
        messageRepository.save(messageNew)
        return fromDayStart()
    }

    @Transactional
    override fun after(messageId: Long): List<MessageToRenderDTO> {
        val thisMessage = messageRepository.findById(messageId)
        return messageRepository
            .findAllByCreatedAfterOrderByCreatedAsc(Timestamp.valueOf(thisMessage.get().created.toLocalDateTime()))
            .mapToViewModel()
    }
}