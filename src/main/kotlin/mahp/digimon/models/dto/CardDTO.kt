package mahp.digimon.models.dto

import mahp.digimon.enums.CardType
import mahp.digimon.enums.Color
import mahp.digimon.models.Card
import java.time.LocalDate
import java.time.LocalDateTime

class CardDTO(
    val name: String,
    val code: String,
    val color: String,
    val type: String,
    val memoryCost: Int,
    val evolutionCost: Int?,
    val mainText: String,
    val lowerText: String?,
    val level: Int? = null,
    val releaseDay: LocalDate?,
){
    fun toR2DBC(): Card = Card(
        null,
        name,
        code,
        color = Color.fromString(color).name,
        type = CardType.fromString(type).name,
        memoryCost,
        evolutionCost,
        mainText,
        lowerText,
        level,
        releaseDay,
        LocalDateTime.now(),
    )
}
