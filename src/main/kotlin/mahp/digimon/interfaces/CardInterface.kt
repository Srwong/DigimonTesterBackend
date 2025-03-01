package mahp.digimon.interfaces

import mahp.digimon.models.Card
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CardInterface: ReactiveCrudRepository<Card, Int> {
    @Query(value =
        """
            SELECT * FROM cards as c
            WHERE
                (:name IS NULL OR c.name LIKE '%' || :name || '%') AND
                (:color IS NULL OR c.color = :color) AND
                (:type IS NULL OR c.type = :type) AND
                (:memory_cost IS NULL OR c.memory_cost = :memory_cost) AND
                (:evolution_cost IS NULL OR c.evolution_cost = :evolution_cost) AND
                (:main_text IS NULL OR c.main_text LIKE '%' || :main_text ||'%') AND
                (:lower_text IS NULL OR c.lower_text LIKE '%' || :lower_text || '%') AND
                (:level IS NULL OR c.level = :level) AND
                (:expansion IS NULL OR c.code LIKE '%' || :expansion || '%')
            ORDER BY c.created_at DESC
            LIMIT :elements
            OFFSET :page * :elements
        """)
    fun findAllWithFilters(
        @Param("name") name: String?,
        @Param("color") color: String?,
        @Param("type") type: String?,
        @Param("memory_cost") memoryCost: Int?,
        @Param("evolution_cost") evolutionCost: Int?,
        @Param("main_text") mainText: String?,
        @Param("lower_text") lowerText: String?,
        @Param("level") level: Int?,
        @Param("expansion") expansion: String?,
        @Param("page") page: Int,
        @Param("elements") elements: Int,
    ): Flux<Card>

    @Query( value =
        """
             SELECT * FROM cards as c
             WHERE c.code = :code
        """
    )
    fun findCardByCode(
        @Param("code") code: String
    ): Mono<Card>

    @Query( value =
    """
        DELETE FROM cards as c
        WHERE c.code = :code
    """)
    fun deleteByCode(
        @Param("code") code: String
    ): Mono<Void>
}
