package com.example.darkenwald

import com.example.darkenwald.domains.ContentType
import com.example.darkenwald.domains.MessageEntity
import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import com.example.darkenwald.models.PlayerDto
import com.example.darkenwald.repository.MessageRepository
import com.example.darkenwald.repository.PlayerRepository
import java.net.URL
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.sql.Timestamp






        fun MessageDto.asMessageEntity(contentType: ContentType = ContentType.MARKDOWN, playerRepository: PlayerRepository):
                MessageEntity = MessageEntity().also {

            it.text = this.text
            it.created = Timestamp.valueOf(this.created)
            it.playerEntity = playerRepository.findById(this.userID!!).get()
            it.contentType = contentType
        }

        fun PlayerDto.asPlayerEntity(): PlayerEntity = PlayerEntity().also {
            it.name = this.name
            it.avatarImageLink = this.avatarImageLink.toString()
            it.mail = this.mail.toString()
            it.password = this.password
        }

        fun MessageEntity.asViewModel(): MessageToRenderDTO = MessageToRenderDTO(
            text, created.toLocalDateTime(), updated?.toLocalDateTime(), playerEntity.name, playerEntity.avatarImageLink
        )


        fun List<MessageEntity>.mapToViewModel(): List<MessageToRenderDTO> = map { it.asViewModel() }

        fun ContentType.render(text: String): String = when (this) {
            ContentType.PLAIN -> text
            ContentType.MARKDOWN -> {
                val flavour = CommonMarkFlavourDescriptor()
                HtmlGenerator(
                    text,
                    MarkdownParser(flavour).buildMarkdownTreeFromString(text), flavour
                ).generateHtml()
            }
        }

        fun MessageDto.asRendered(contentType: ContentType = ContentType.MARKDOWN): MessageDto =
            this.copy(text = contentType.render(this.text))
