package mahp.digimon.controllers

import mahp.digimon.helpers.CardHelper
import mahp.digimon.models.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CardControllerAdditionTest {

    @Autowired
    lateinit var webClient: WebTestClient

    var cardHelper = CardHelper()

    @Test
    fun `POST to add a new card`(){
        val newCard = cardHelper.newCard()
        val json =  cardHelper.toJson(newCard)

        val result = webClient.post().uri("/v1/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(json)).exchange()
            .expectStatus().isCreated
            .expectBody(String::class.java).returnResult().responseBody

        assertEquals("Created card ${newCard.name}", result)
    }

    @Test
    fun `Get specific card by code`(){
        val newCard = cardHelper.newCard()
        val json =  cardHelper.toJson(newCard)

        webClient.post().uri("/v1/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(json)).exchange()
            .expectStatus().isCreated
            .expectBody(String::class.java).returnResult().responseBody

        val result = webClient.get().uri("/v1/cards/${newCard.code}").exchange()
            .expectStatus().isOk
            .expectBody(Card::class.java)
            .returnResult().responseBody

        assertTrue(result != null)
        assertEquals(newCard.code, result!!.code)
        assertEquals(newCard.name, result.name)
        assertEquals(newCard.type, result.type)
        assertEquals(newCard.level, result.level)
        assertEquals(newCard.color, result.color)
        assertEquals(newCard.memoryCost, result.memoryCost)
        assertEquals(newCard.evolutionCost, result.evolutionCost)
        assertEquals(newCard.mainText, result.mainText)
        assertEquals(newCard.lowerText, result.lowerText)
    }

    @Test
    fun `Delete card`(){
        webClient.delete().uri("/v1/cards/BT1-026").exchange()
            .expectStatus().isNoContent
    }
}