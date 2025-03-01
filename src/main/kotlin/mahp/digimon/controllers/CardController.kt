package mahp.digimon.controllers

import mahp.digimon.models.Card
import mahp.digimon.models.CardDTO
import mahp.digimon.services.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("v1/cards")
class CardController(
    private val cardService: CardService,
) {
    @GetMapping
    fun getCards(
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "color", required = false) color: String?,
        @RequestParam(name = "type", required = false) type: String?,
        @RequestParam(name = "memoryCost", required = false) memoryCost: Int?,
        @RequestParam(name = "evolutionCost", required = false) evolutionCost: Int?,
        @RequestParam(name = "mainText", required = false) mainText: String?,
        @RequestParam(name = "lowerText", required = false) lowerText: String?,
        @RequestParam(name = "level", required = false) level: Int?,
        @RequestParam(name = "expansion", required = false) expansion: String?,
        @RequestParam(name = "page", required = false) page: Int = 0,
        @RequestParam(name = "elements", required = false) elements: Int = 20,
    ): Flux<Card> {
        return cardService.getAllCards(
            name, color, type, memoryCost, evolutionCost, mainText, lowerText, level, expansion, page, elements
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCard(@RequestBody card: CardDTO): Mono<String> {
        return cardService.addCard(card)
    }

    @GetMapping("/{cardCode}")
    fun getCardByCode(@PathVariable cardCode: String): Mono<Card> {
        return cardService.getCardByCode(cardCode)
    }

    @DeleteMapping("/{cardCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCardByCode(@PathVariable cardCode: String): Mono<Void> {
        return cardService.deleteByCode(cardCode)
    }
}
