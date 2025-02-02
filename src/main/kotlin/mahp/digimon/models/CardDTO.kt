package mahp.digimon.models

import mahp.digimon.enums.CardType
import mahp.digimon.enums.Color

class CardDTO(
    val name: String,
    val code: String,
    val color: String,
    val type: String,
    val memoryCost: Int,
    val mainText: String,
    val lowerText: String,
    val level: Int? = null,
){
    fun toJPA(): Card = Card(
        null,
        name,
        code,
        color = Color.fromString(color).name,
        type = CardType.fromString(type).name,
        memoryCost,
        mainText,
        lowerText,
        level,
    )
}