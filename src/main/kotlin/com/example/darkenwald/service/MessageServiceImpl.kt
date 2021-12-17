package com.example.darkenwald.service

import com.example.darkenwald.asMessageEntity
import com.example.darkenwald.mapToViewModel
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import com.example.darkenwald.repository.MessageRepository
import com.example.darkenwald.repository.PlayerRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime

@Service
class MessageServiceImpl(
    val messageRepository: MessageRepository,
    val playerRepository: PlayerRepository,
) : MessageService{

    private var startTime: LocalDateTime = LocalDateTime.now().minusWeeks(1)

    @EventListener()
    fun getStartTime(event: DayBreakEvent){
        startTime = event.dateTime
    }

    override fun fromDayStart(): List<MessageToRenderDTO> {
        return messageRepository.findAllByCreatedAfterOrderByCreatedDesc(Timestamp.valueOf(startTime)).mapToViewModel()
    }

    override fun post(message: MessageDto): List<MessageToRenderDTO> {
        val savedMessage = messageRepository.save(message.asMessageEntity(playerRepository = playerRepository))
        return after(savedMessage.id!!)
    }

    override fun after(messageId: Long): List<MessageToRenderDTO> {
        val thisMessage = messageRepository.findById(messageId)
        return messageRepository
            .findAllByCreatedAfterOrderByCreatedDesc(Timestamp.valueOf(thisMessage.get().created.toLocalDateTime()))
            .mapToViewModel()
    }
}