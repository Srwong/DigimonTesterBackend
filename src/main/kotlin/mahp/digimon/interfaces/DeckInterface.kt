package mahp.digimon.interfaces

import mahp.digimon.models.Deck
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface DeckInterface: ReactiveCrudRepository<Deck, Int> {
    @Query("""
        SELECT * FROM decks AS d
        SORT BY d.name ASC
        LIMIT :elements
        OFFSET :page + :elements
    """)
    fun getDecks(
        @Param("elements") elements: Int,
        @Param("page") page: Int,
    ): Flux<Deck>
}