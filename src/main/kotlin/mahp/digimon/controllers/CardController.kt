package mahp.digimon.controllers

import mahp.digimon.models.Card
import mahp.digimon.models.CardDTO
import mahp.digimon.services.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService,
) {
    @GetMapping
    fun getCards(): Flux<Card> {
        return cardService.getAllCards()
    }

    @PostMapping
    fun addCard(@RequestBody card: CardDTO): Mono<String> {
        return cardService.addCard(card)
    }
}