package org.acme.kafka

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.common.QuarkusTestResource
import org.eclipse.microprofile.reactive.messaging.spi.Connector
import io.smallrye.reactive.messaging.memory.InMemoryConnector

import org.junit.jupiter.api.Test
import org.awaitility.Awaitility.await
import org.hamcrest.CoreMatchers.{is, notNullValue}
import org.junit.jupiter.api.Assertions.*
import jakarta.ws.rs.core.MediaType

import jakarta.inject.Inject
import com.fasterxml.jackson.databind.ObjectMapper

import helper.*

@QuarkusTest
@QuarkusTestResource(classOf[KafkaResourceLivecycleManager])
class ArticleProducerConsumerTest(@Connector("smallrye-in-memory") connector: InMemoryConnector):

    @Inject
    var objectMapper: ObjectMapper = null

    @Test
    def testArticleString() =
        val article = Article("1", "Test article")
        assertEquals(article.toString(), s"Article(id=1, title=Test article, processed=false)")

    @Test
    def testUUIDGenerator() =
        Given()
            .When(
                _.get("/article/uuid")
            ).Then(res =>
                res.statusCode(200)
                res.body(is(notNullValue()))
            )

    @Test
    def testArticlePost() =
        // Create a new article
        val article     = Article("1", "Test article")
        val articleJson = objectMapper.writeValueAsString(article)

        Given()
            .When(
                _.body(articleJson).contentType(MediaType.APPLICATION_JSON).post("/article")
            ).Then(res =>
                res.statusCode(200)
                res.body(is(article.id))
            )

    @Test
    def testPostedArticleToKafka() =
        // Check if previous test posted the article to Kafka
        val article  = Article("1", "Test article")
        val articles = connector.sink[Article]("articles")
        await().until(() => articles.received().size() == 1)
        val receivedArticle = articles.received().get(0).getPayload()
        assertEquals(receivedArticle, article)

end ArticleProducerConsumerTest