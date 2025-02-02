package mahp.digimon.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController{
    @GetMapping("/health")
    fun alive(): ResponseEntity<HttpStatus> {
        return ResponseEntity.ok(HttpStatus.OK)
    }
}
