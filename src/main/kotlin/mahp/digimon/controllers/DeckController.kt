package mahp.digimon.controllers

import mahp.digimon.models.Deck
import mahp.digimon.models.dto.DeckDTO
import mahp.digimon.services.DeckService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/decks")
class DeckController(
    val deckService: DeckService,
) {

    @GetMapping
    fun getDecks(
        @RequestParam(required = false) page: Int = 0,
        @RequestParam(required = false) elements: Int = 10,
    ): Flux<Deck> {
        return deckService.getDecks(elements,page)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeck(
        @RequestBody deckData: DeckDTO,
    ): Mono<Deck> {
        return deckService.createDeck(deckData)
    }
}