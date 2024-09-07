package com.horobar.library.article

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import jakarta.transaction.Transactional
import java.util.*

@ApplicationScoped
class ArticleRepository : PanacheRepository<Article> {

    fun onStart(@Observes env: StartupEvent) {
        this.example()
    }

    fun findByTitle(title: String): Article? {
        return find("title", title).firstResult()
    }

    fun findById(id: UUID): Article {
        return find("id", id).firstResult()
            ?: throw IllegalArgumentException("Article not found")
    }


    @Transactional
    final fun example() {
        val article = Article().apply {
            title = "Hello, world!"
            content = "This is a test article."
        }
        this.persist(article)
    }
}