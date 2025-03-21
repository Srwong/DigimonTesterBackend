package mahp.digimon.helpers

import mahp.digimon.models.dto.CardDTO

class CardHelper {
    fun newCard() = CardDTO(
        name = "WarGreymon",
        code = "BT1-026",
        color = "RED",
        type = "TAMER",
        memoryCost = 7,
        mainText = "This Digimon gains Security Attack +1.",
        lowerText = "Digivolve: 3 from MetalGreymon",
        level = 6,
        evolutionCost = 6
    )

    fun toJson(card: CardDTO) = """
            {
              "name": "${card.name}",
              "code": "${card.code}",
              "color": "${card.color}",
              "type": "${card.type}",
              "memoryCost": ${card.memoryCost},
              "mainText": "${card.mainText}",
              "lowerText": "${card.lowerText}",
              "level": ${card.level},
              "evolutionCost": ${card.evolutionCost}
            }
        """.trimIndent()

}