package mahp.digimon.enums

enum class CardType(val type: String) {
    TAMER("tamer"),
    DIGIMON("digimon"),
    EGG("egg"),
    OPTION("option");

    companion object {
        fun fromString(type: String): CardType {
            return CardType.entries.find { it.type.equals( type, ignoreCase = true)} ?: EGG
        }
    }
}
