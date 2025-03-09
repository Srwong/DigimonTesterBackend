package mahp.digimon.models

import mahp.digimon.enums.CardType
import mahp.digimon.enums.Color
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table("cards")
data class Card (
    @Id
    val id: Int? = null, //if this is null webflux inserts, otherwise updates
    val name: String,
    val code: String,
    val color: String,
    val type: String,
    @Column("memory_cost")
    val memoryCost: Int,
    @Column("evolution_cost")
    val evolutionCost: Int?,
    @Column("main_text")
    val mainText: String,
    @Column("lower_text")
    val lowerText: String? = null,
    val level: Int? = null,
    @Column("release_day")
    val releaseDay: LocalDate? = null,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    constructor() : this(
        null, "", "", Color.RED.name, CardType.DIGIMON.name,
        0, null,"", "", null, null, LocalDateTime.now(),
    )
}
