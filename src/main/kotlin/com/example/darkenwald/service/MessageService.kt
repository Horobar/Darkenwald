package com.example.darkenwald.service

import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import javax.servlet.http.HttpSession


interface MessageService {

    fun fromDayStart(): List<MessageToRenderDTO>

    fun post(message: MessageDto, playerName: String): List<MessageToRenderDTO>

    fun after(messageId: Long): List<MessageToRenderDTO>
}