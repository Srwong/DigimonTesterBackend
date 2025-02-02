package mahp.digimon.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import mahp.digimon.enums.CardType
import mahp.digimon.enums.Color

@Entity
data class Card (
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Int? = 0,
    val name: String,
    val code: String,
    val color: String,
    val type: String,
    @Column(name = "memory_cost")
    val memoryCost: Int,
    @Column(name = "main_text")
    val mainText: String,
    @Column(name = "lower_text")
    val lowerText: String,
    val level: Int? = null,
) {
    constructor() : this(0, "", "", Color.RED.name, CardType.DIGIMON.name, 0, "", "", null)
}
