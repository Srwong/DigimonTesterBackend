package mahp.digimon.services

import mahp.digimon.interfaces.CardInterface
import mahp.digimon.models.Card
import mahp.digimon.models.CardDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CardService(
    private val cardInterface: CardInterface,
) {
    fun getAllCards(
        name: String?,
        color: String?,
        type: String?,
        memoryCost: Int?,
        evolutionCost: Int?,
        mainText: String?,
        lowerText: String?,
        level: Int?,
        expansion: String?,
        page: Int,
        elements: Int,
    ): Flux<Card> {
        return cardInterface.findAllWithFilters(
            name, color, type, memoryCost, evolutionCost, mainText, lowerText, level, expansion, page, elements
        )
    }

    fun addCard(card: CardDTO): Mono<String> {
        return cardInterface.save(card.toR2DBC())
            .map { _ -> "Created card ${card.name}" }
            .onErrorResume {
                    ex ->
                println("Error in addCard: ${ex.message}")
                Mono.just(ex.message.toString())
            }
    }

    fun getCardByCode(code: String): Mono<Card> {
        return cardInterface.findCardByCode(code)
    }
}
