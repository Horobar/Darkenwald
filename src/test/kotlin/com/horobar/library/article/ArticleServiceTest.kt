package com.horobar.library.article

import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*


@QuarkusTest
class ArticleServiceTest {

    @Inject
    lateinit var articleService: ArticleService
    @InjectMock
    lateinit var articleRepository: ArticleRepository

    lateinit var id: UUID
    lateinit var testArticle: Article

    private val renderedContent = "<h1>Test Content</h1>"

    @BeforeEach
    fun setup() {

        id = UUID.randomUUID()
        testArticle = Article().apply {
            this.id = id
            title = "Test Article"
            content = "# Test Content"
        }
        Mockito.`when`( articleRepository.findById(id)).thenReturn(testArticle)
    }

    @Test
    fun testGetArticle() {
        val article = articleService.getArticle(id)
        assertNotNull(article)
//        assertEquals(id, article.id)
        assertEquals("Test Article", article.title)
        assertEquals(renderedContent, article.content)
    }
}