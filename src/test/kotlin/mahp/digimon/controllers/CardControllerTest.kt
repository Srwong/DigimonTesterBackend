package mahp.digimon.controllers

import mahp.digimon.helpers.SqlScriptRunner
import mahp.digimon.models.Card
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
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
class CardControllerTest{

    @Autowired
    lateinit var webClient: WebTestClient

    @Autowired
    private lateinit var sqlScriptRunner: SqlScriptRunner

    @BeforeEach
    fun setUp(){
        sqlScriptRunner.executeScript("/sql/add_cards.sql") // Execute SQL script
    }

    @AfterEach
    fun cleanDatabase(){
        sqlScriptRunner.executeScript("/sql/delete_cards.sql")
    }

    @Test
    fun `getCards, Call with default params`(){
        val result = webClient.get().uri("/v1/cards").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(9, result?.size)
    }

    @Test
    fun `POST to add a new card`(){
        val newCard = """
            {
              "name": "WarGreymon2",
              "code": "BT1-026",
              "color": "RED",
              "type": "TAMER",
              "memoryCost": 7,
              "mainText": "This Digimon gains Security Attack +1.",
              "lowerText": "Digivolve: 3 from MetalGreymon",
              "level": 6
            }
        """.trimIndent()

        val result = webClient.post().uri("/v1/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(newCard)).exchange()
            .expectStatus().isCreated
            .expectBody(String::class.java).returnResult().responseBody

        assertEquals("Created card WarGreymon2", result)
    }

    @Test
    fun `getCards, filter by name`(){
        val name = "d-"
        val result = webClient.get().uri("/v1/cards?name=$name").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(0, result?.size)
        for(r in result!!) (name == r.name)
    }

    @Test
    fun `getCards, Check pagination`(){
        val firstPage = webClient.get()
            .uri("/v1/cards?elements=5")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        val secondPage = webClient.get()
            .uri("/v1/cards?page=1&elements=5")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(5, firstPage?.size)
        assertEquals(4, secondPage?.size)
    }

    @Test
    fun `getCards, filter by color`(){
        val color = "BLUE"
        val result = webClient.get().uri("/v1/cards?color=$color").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(3, result?.size)
        for(r in result!!) assertTrue(color == r.color)
    }

    @Test
    fun `getCards, filter by type`(){
        val type = "DIGIMON"
        val result = webClient.get().uri("/v1/cards?type=$type").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(5, result?.size)
        for(r in result!!) assertTrue(type == r.type)
    }

    @ParameterizedTest
    @ValueSource(ints = [3,4,5,6])
    fun `getCards, filter by memoryCost`(memoryCost: Int){
        val result = webClient.get().uri("/v1/cards?memoryCost=$memoryCost").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(2, result?.size)
        for(r in result!!) assertTrue(memoryCost == r.memoryCost)
    }

    @Test
    fun `getCards, filter by evolutionCost`(){
        val evolutionCost = 5
        val result = webClient.get().uri("/v1/cards?evolutionCost=$evolutionCost").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(2, result?.size)
        for(r in result!!) assertTrue(evolutionCost == r.evolutionCost)
    }

    @Test
    fun `getCards, filter by mainText`(){
        val mainText = "text2"
        val result = webClient.get().uri("/v1/cards?mainText=$mainText").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(5, result?.size)
        for(r in result!!) assertTrue(mainText in r.mainText)
    }

    @Test
    fun `getCards, filter by lowerText`(){
        val lowerText = "effect"
        val result = webClient.get().uri("/v1/cards?lowerText=$lowerText").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(5, result?.size)
        for(r in result!!) assertTrue(r.lowerText?.contains(lowerText) == true)
    }

    @Test
    fun `getCards, filter by level`(){
        val level = 4
        val result = webClient.get().uri("/v1/cards?level=$level").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(3, result?.size)
        for(r in result!!) assertTrue(r.level == level)
    }

    @ParameterizedTest
    @CsvSource(value = ["BT02,2", "BT03,2", "BT04,5"])
    fun `getCards, filter by expansion`(expansion: String, elements: Int){
        val result = webClient.get().uri("/v1/cards?expansion=$expansion").exchange()
            .expectStatus().isOk
            .expectBodyList(Card::class.java)
            .returnResult().responseBody

        assertEquals(elements, result?.size)
        for(r in result!!) assertTrue(expansion in r.code)
    }
}
