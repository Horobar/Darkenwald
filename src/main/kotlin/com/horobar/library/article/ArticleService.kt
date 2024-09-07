package com.horobar.library.article

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class ArticleService(
    private val articleRepository: ArticleRepository
) {

    private val options = MutableDataSet()
    private val parser = Parser.builder(options).build()
    private val renderer = HtmlRenderer.builder(options).build()

    fun getRawArticle(id: UUID): Article {
        return articleRepository.findById(id) ?: throw IllegalArgumentException("Article not found")
    }

    fun getArticle(id: UUID): Article {
        val article = articleRepository.findById(id) ?: throw IllegalArgumentException("Article not found")
        return renderMarkdown(article)
    }

    fun createArticle(title: String, content: String): Article {
        val article = Article().apply {
            this.title = title
            this.content = content
        }
        articleRepository.persist(article)
        return renderMarkdown(article)
    }

    fun updateArticle(id: UUID, title: String, content: String): Article {
        val article = articleRepository.findById(id)
        return article.apply {
            this.title = title
            this.content = content
        }.also {
            articleRepository.persist(article)
        }
    }

    fun deleteArticle(id: UUID) {
        val article = articleRepository.findById(id)
        article.let { articleRepository.delete(it) }
    }


    private fun renderMarkdown(article: Article) = article.apply {
        if (content != null) {
            content = renderer.render(parser.parse(content!!))
        }
    }
}

