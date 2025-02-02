package mahp.digimon.services

import mahp.digimon.interfaces.CardInterface
import mahp.digimon.models.Card
import mahp.digimon.models.CardDTO
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardInterface: CardInterface,
) {
    fun getAllCards(): List<Card> {
        return cardInterface.findAll().toList()
    }

    fun addCard(card: CardDTO): String {
        try{
            cardInterface.save(card.toJPA())
        } catch (ex: Exception){
            println("Error in addCard: ${ex.message}")
            return ex.message.toString()
        }
        return "ok"
    }
}
