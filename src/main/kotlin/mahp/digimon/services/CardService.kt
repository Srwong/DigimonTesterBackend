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
    fun getAllCards(): Flux<Card> {
        return cardInterface.findAll()
    }

    fun addCard(card: CardDTO): Mono<String> {
        return cardInterface.save(card.toJPA())
            .map { _ -> "ok" }
            .onErrorResume {
                    ex ->
                println("Error in addCard: ${ex.message}")
                Mono.just(ex.message.toString())
            }
    }
}
