package com.example.darkenwald.controller


import com.example.darkenwald.domains.PlayerEntity
import com.example.darkenwald.models.MessageDto
import com.example.darkenwald.models.MessageToRenderDTO
import com.example.darkenwald.models.PlayerDto
import com.example.darkenwald.models.PlayerInSession
import com.example.darkenwald.service.EditPlayerService
import com.example.darkenwald.service.LoginService
import com.example.darkenwald.service.MessageService
import com.example.darkenwald.service.RegistrationService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class HtmlController(
    private val messageService: MessageService,
    private val loginService: LoginService,
    private val registrationService: RegistrationService,
    private val editPlayerService: EditPlayerService
) {
    @GetMapping("messagePost.html")
    fun getFragemnt(): ModelAndView{
        return ModelAndView("fragments/messagePost")
    }
    /**
     * ModelAttributes: just to give Infos about the classes to the templates
     */
    @ModelAttribute("playerEntities")
    fun playerEntities(): List<PlayerEntity> = mutableListOf()

    @ModelAttribute("messages")
    fun addMessages(): List<MessageToRenderDTO> = messageService.fromDayStart()

    @ModelAttribute("postedMessage")
    fun messageDto() = MessageDto("", LocalDateTime.MIN)

    @ModelAttribute("player")
    fun playerDto() = PlayerDto("", "static/avatars/default_avatar.png", "", null, null)

    @ModelAttribute("warnings")
    fun warnings() = mutableListOf<String>()

    @ModelAttribute("playerInSession")
    fun playerInSession() = PlayerInSession("","", false)

    /**
     * send user to the start site for login
     */
    @GetMapping(value = ["/", "index.html"])
    fun index(request: HttpServletRequest): ModelAndView {

        val modelAndView = ModelAndView("index")
        modelAndView.addObject("validationFailed", false)
        return modelAndView
    }

    /**
     * get login data
     */
    @PostMapping(value = ["/", "/loginEndpoint"])
    fun login(
        @RequestParam playerName: String, @RequestParam password: String,
        request: HttpServletRequest
    ): ModelAndView {

        val verifiedPlayer = loginService.verifyLogin(playerName, password)

        return if (verifiedPlayer != null) {
            request.session.invalidate()
            request.session.setAttribute("playerInSession", PlayerInSession(
                name = verifiedPlayer.name,
                avatarImageLink = verifiedPlayer.avatarImageLink,
                isAdmin = verifiedPlayer.isAdmin
            ))
            request.session.setAttribute("loggedIn", true)

            val modelAndView = ModelAndView("darkenwald")
            val currentPlayer = request.session.getAttribute("playerInSession") as PlayerInSession

            if(currentPlayer.isAdmin) {
                modelAndView.addObject("playerEntities", editPlayerService.getAllPlayerEntities())
            }

            modelAndView.addObject("playerInSession", currentPlayer)
            modelAndView
        } else {
            val modelAndView = ModelAndView("index")
            modelAndView.addObject("validationFailed", true)
            modelAndView
        }
    }

    /**
     * leads to the registration site
     */
    @GetMapping(value = ["/registration", "registration.html"])
    fun registration(): ModelAndView {
        return ModelAndView("registration")
    }

    /**
     * Take the player datas from the registration site, send them to validation and send the player back to the login or
     * sends a warning if datas are not valid
     */
    @PostMapping(value = ["/registration"])
    fun registerPlayer(@ModelAttribute player: PlayerDto): ModelAndView {
        val warnings = registrationService.verifyRegistration(player)

        val modelAndView = ModelAndView("registration")
        modelAndView.addObject("warnings", warnings)
        return modelAndView
    }


    /**
     * send user to main site
     */
    @GetMapping(value = ["/darkenwald", "darkenwald.html"])
    fun darkenwald(request: HttpServletRequest): ModelAndView {
        if (request.session.getAttribute("loggedIn") == null) {
            return ModelAndView("index")
        }
        val modelAndView = ModelAndView("darkenwald")
        modelAndView.addObject("playerInSession", request.session.getAttribute("playerInSession"))
        modelAndView.addObject("messages", messageService.fromDayStart())
        return modelAndView
    }

    /**
     * save the incoming message and returns all messages
     */
    @RequestMapping(value = ["/darkenwald"], method = [RequestMethod.POST])
    fun takeMessage(@ModelAttribute message: MessageDto, model: Model, session: HttpSession): Model {
        val playerName = (session.getAttribute("playerInSession") as PlayerInSession).name
        message.created = LocalDateTime.now()
        model["messages"] = messageService.post(message, playerName)
        return model
    }
}