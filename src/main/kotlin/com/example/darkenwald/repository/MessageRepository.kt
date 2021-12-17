package com.example.darkenwald.repository

import com.example.darkenwald.domains.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime

@Repository
interface MessageRepository : JpaRepository<MessageEntity, Long> {

    fun findAllByCreatedAfterOrderByCreatedDesc(created: Timestamp): List<MessageEntity>

}