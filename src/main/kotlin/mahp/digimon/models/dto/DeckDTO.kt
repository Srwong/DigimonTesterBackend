package mahp.digimon.models.dto

import mahp.digimon.models.Deck

class DeckDTO(
    val id: Int? = null,
    val name: String,
    val owner: Int,
    val main: String? = null,
    val eggs: String? = null,
) {
    fun toR2DBC() = Deck(id, name, owner, main, eggs)
}
