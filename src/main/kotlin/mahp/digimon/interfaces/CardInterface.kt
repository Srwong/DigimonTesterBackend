package mahp.digimon.interfaces

import mahp.digimon.models.Card
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CardInterface: ReactiveCrudRepository<Card, Int> {
    fun getCardById(id: Int): Mono<Card>
}
