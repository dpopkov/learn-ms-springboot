package learn.mssbkt.multiplicationkt.challenge

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

/**
 * Попытка решения пользователя.
 */
data class ChallengeAttemptDTO(
    @field:Min(1)
    @field:Max(99)
    val factorA: Int,
    @field:Min(1)
    @field:Max(99)
    val factorB: Int,
    @field:NotBlank
    val userAlias: String,
    @field:Positive(message = "How could you possibly get a negative result here? Try again.")
    val guess: Int,
)
