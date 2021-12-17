package com.example.darkenwald.domains


import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "MESSAGE")
class MessageEntity {
    @Id
    @GeneratedValue
    @Column(name = "message_id", nullable = false)
    open var id: Long? = null


    @Column(name = "TEXT")
    lateinit var text: String

    @Column(name = "ContentType", nullable = false)
    lateinit var contentType: ContentType

    @Column(name = "CREATED", nullable = false)
    lateinit var created: Timestamp

    @Column(name = "UPDATED", nullable = true)
    var updated: Timestamp? = null

    @ManyToOne
    @JoinColumn(name = "ID")
    lateinit var playerEntity: PlayerEntity
}

enum class ContentType {
    PLAIN, MARKDOWN
}