package learn.mssbkt.multiplicationkt.challenge

/**
 * Попытка решения пользователя.
 */
data class ChallengeAttemptDTO(
    val factorA: Int,
    val factorB: Int,
    val userAlias: String,
    val guess: Int,
)
