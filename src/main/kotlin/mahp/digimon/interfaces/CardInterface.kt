package mahp.digimon.interfaces

import mahp.digimon.models.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardInterface: JpaRepository<Card, Int> {
    fun getCardById(id: Int): Card
}
