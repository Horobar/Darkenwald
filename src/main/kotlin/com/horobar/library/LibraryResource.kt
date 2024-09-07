package com.horobar.library

import com.horobar.library.model.Category
import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.Location
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.jboss.resteasy.reactive.RestPath

@Path("/library")
class LibraryResource(
    private val libraryService: LibraryService
) : HxController() {

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun librarySite(categories: List<Category>): TemplateInstance
        @JvmStatic
        external fun `librarySite$main`(categories: List<Category>): TemplateInstance
        @JvmStatic
        external fun `librarySite$catalog`(categories: List<Category>): TemplateInstance
    }

    @GET
    fun site(): TemplateInstance {
        val categories = libraryService.allCategories()
        if (isHxRequest) {
            return Templates.`librarySite$main`(categories)
        }
        return Templates.librarySite(categories)
    }

    @GET
    @Path("/category/{name}")
    fun category(
        @RestPath
        name: String?
    ): TemplateInstance {
        if (name == "all") {
            val categories = libraryService.allCategories()
            return Templates.`librarySite$catalog`(categories)
        } else {
            val category = libraryService.category(name!!)
            return Templates.`librarySite$catalog`(category)
        }
    }
}

