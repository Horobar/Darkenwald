package com.horobar.library.article

import io.quarkiverse.renarde.htmx.HxController
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestPath
import java.util.*

@Path("/article")
class ArticleResource(
    private val articleService: ArticleService
) : HxController() {

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun article(article: Article): TemplateInstance

        @JvmStatic
        external fun editArticle(article: Article): TemplateInstance
//        @JvmStatic
//        external fun createArticleForm(title: String, content: String): TemplateInstance
    }

    @GET
    fun edit(
        @RestPath id: String
    ): TemplateInstance {
        val article = articleService.getRawArticle(UUID.fromString(id))
        return Templates.editArticle(article)
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun byId(
        @RestPath id: String
    ): TemplateInstance {
        val article = articleService.getArticle(UUID.fromString(id))
        return Templates.article(article)
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun update(
        @RestPath id: String,
        @FormParam("title") title: String,
        @FormParam("content") content: String
    ): TemplateInstance {
        articleService.updateArticle(UUID.fromString(id), title, content)
        return byId(id)
    }
}