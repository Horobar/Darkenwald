package com.horobar.library.character

import com.horobar.library.character.PersonDomain.BASE_PATH
import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.jboss.resteasy.reactive.RestPath
import java.util.*

object PersonDomain {
    const val BASE_PATH = "/persons"
}

@Path(BASE_PATH)
class PersonResource(
    private val repository: PersonRepository
): HxController() {

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun person(person: Person): TemplateInstance
        @JvmStatic
        external fun `person$main`(person: Person): TemplateInstance
    }

    @GET
    @Path("/{id}")
    fun person(
        @RestPath id: String
    ): TemplateInstance {
        val character = repository.findById(UUID.fromString(id))
            ?: throw IllegalArgumentException("Character not found")
        if (isHxRequest) {
            return Templates.`person$main`(character)
        }
        return Templates.person(character)
    }
}