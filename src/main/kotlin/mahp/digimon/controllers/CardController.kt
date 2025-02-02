package mahp.digimon.controllers

import mahp.digimon.models.Card
import mahp.digimon.models.CardDTO
import mahp.digimon.services.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService,
) {
    @GetMapping
    fun getCards(): ResponseEntity<List<Card>>{
        val cards = cardService.getAllCards()
        return ResponseEntity.ok().body(cards)
    }

    @PostMapping
    fun addCard(@RequestBody card: CardDTO): ResponseEntity<HttpStatus>{
        val status = cardService.addCard(card)
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED)
    }
}