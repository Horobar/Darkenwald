package com.horobar.infra.user

import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.*

@Path("/user")
class UserResource(
    private val userService: UserService,
    private val jwt: JsonWebToken,
) : HxController() {

    private val externalId: UUID
        get() = UUID.fromString(jwt.subject)

    @Suppress("FunctionName", "unused")
    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun `fragments$avatar`(name: String): TemplateInstance

        @JvmStatic
        external fun settingsView(user: UserEntity, referer: String): TemplateInstance
    }


    @GET
    fun avatar(): TemplateInstance {
        val user = userService.findUserByExternalId(externalId)
        return Templates.`fragments$avatar`(user.name)
    }

    @GET
    fun settings(
        @HeaderParam("Referer") referer: String = "/",
    ): TemplateInstance {
        val user = userService.findUserByExternalId(externalId)
        return Templates.settingsView(user, referer)
    }

    @PUT
    fun update(
        @BeanParam userDTO: UserDTO,
        @FormParam("referer") referer: String = "/",
    ): Response {
        userDTO.userId = externalId
        userService.updateUser(userDTO)
        return Response.ok()
            .header("HX-Redirect", referer)
            .header("HX-Trigger", "userUpdated")
            .build()
    }
}

class UserDTO {
    lateinit var userId: UUID

    @FormParam("name")
    var name: String? = null

    @FormParam("preferredName")
    var preferredName: String? = null
}