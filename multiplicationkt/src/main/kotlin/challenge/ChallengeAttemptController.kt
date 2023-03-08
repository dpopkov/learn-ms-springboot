package learn.mssbkt.multiplicationkt.challenge

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/attempts")
class ChallengeAttemptController(
    private val challengeService: ChallengeService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun postResult(@RequestBody @Valid attemptDTO: ChallengeAttemptDTO): ResponseEntity<ChallengeAttempt> {
        val attempt = challengeService.verifyAttempt(attemptDTO)
        log.info("Verified attempt: {}", attempt)
        return ResponseEntity.ok(attempt)
    }

    @GetMapping
    fun getStatistics(@RequestParam("alias") alias: String): ResponseEntity<List<ChallengeAttempt>> {
        return ResponseEntity.ok(challengeService.getStatsForUser(alias))
    }
}
