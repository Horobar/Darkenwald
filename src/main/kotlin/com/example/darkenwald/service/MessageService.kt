package com.example.darkenwald.service

import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import javax.servlet.http.HttpSession


interface MessageService {

    /**
     * returns all Messages since the last day break events called
     */
    fun fromDayStart(): List<MessageToRenderDTO>

    /**
     * save a message
     */
    fun post(message: MessageDto, playerName: String): List<MessageToRenderDTO>

    /**
     * returns all nessages that whre wrote after a specifc message
     */
    fun after(messageId: Long): List<MessageToRenderDTO>
}