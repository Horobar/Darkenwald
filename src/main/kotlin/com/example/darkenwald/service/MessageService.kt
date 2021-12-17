package com.example.darkenwald.service

import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO


interface MessageService {

    fun fromDayStart(): List<MessageToRenderDTO>

    fun post(message: MessageDto): List<MessageToRenderDTO>

    fun after(messageId: Long): List<MessageToRenderDTO>
}