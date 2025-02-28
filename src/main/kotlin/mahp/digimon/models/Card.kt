package mahp.digimon.models

import mahp.digimon.enums.CardType
import mahp.digimon.enums.Color
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("card")
data class Card (
    @Id
    val id: Int? = null, //if this is null webflux inserts, otherwise updates
    val name: String,
    val code: String,
    val color: String,
    val type: String,
    @Column
    val memoryCost: Int,
    @Column
    val mainText: String,
    @Column
    val lowerText: String,
    val level: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    constructor() : this(
        null, "", "", Color.RED.name, CardType.DIGIMON.name,
        0, "", "", null, LocalDateTime.now(),
    )
}
