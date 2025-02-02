package mahp.digimon.enums

enum class Color(val color: String) {
    RED("red"),
    BLUE("blue"),
    YELLOW("yellow"),
    PURPLE("purple"),
    GREEN("green"),
    BLACK("black"),
    WHITE("white");

    companion object {
        fun fromString(color: String): Color {
            return entries.find { it.color.equals( color, ignoreCase = true)} ?: RED
        }
    }
}
