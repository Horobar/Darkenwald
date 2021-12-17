package com.example.darkenwald.controller


import com.example.darkenwald.asMessageEntity
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import com.example.darkenwald.repository.PlayerRepository
import com.example.darkenwald.service.MessageService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

@Controller
class HtmlController(
    private val messageService: MessageService,
    private val playerRepository: PlayerRepository
) {

    @ModelAttribute("messages")
    fun addMessages(): List<MessageToRenderDTO> = messageService.fromDayStart()

    @ModelAttribute("postedMessage")
    fun messageDto() = MessageDto("",  LocalDateTime.MIN, 0L)

    /**
     * send user to the start site for login
     */
    @GetMapping(value = ["/darkenwald", "darkenwald.html"])
    fun index(): ModelAndView {
        val modelAndView = ModelAndView("darkenwald")
        modelAndView.addObject("messages", messageService.fromDayStart())
        return modelAndView
    }

    @RequestMapping(value = ["/darkenwald"], method = [RequestMethod.POST])
    fun takeMessage(@ModelAttribute message: MessageDto, model: Model, session: HttpSession): Model {
        message.created = LocalDateTime.now()
        message.userID = 1L
        model["messages"] = messageService.post(message)
        return model
    }


}