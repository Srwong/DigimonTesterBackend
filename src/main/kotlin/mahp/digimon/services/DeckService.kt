package mahp.digimon.services

import mahp.digimon.interfaces.DeckInterface
import mahp.digimon.models.Deck
import mahp.digimon.models.dto.DeckDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DeckService(
    val deckInterface: DeckInterface
) {
    fun getDecks(elements: Int, page: Int): Flux<Deck>{
        return deckInterface.getDecks(elements, page)
    }

    fun createDeck(deckData: DeckDTO): Mono<Deck> {
        return deckInterface.save(deckData.toR2DBC())
    }
}
