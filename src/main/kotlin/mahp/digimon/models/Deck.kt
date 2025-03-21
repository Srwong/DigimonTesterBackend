package mahp.digimon.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("decks")
data class Deck(
    @Id
    val id: Int? = null,
    val name: String,
    @Column("owner_id")
    val owner: Int,
    @Column("main_deck")
    val main: String? = null,
    @Column("eggs_deck")
    val eggs: String? = null,
    @Column("created_At")
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
