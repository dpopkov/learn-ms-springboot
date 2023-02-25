package learn.mssbkt.multiplicationkt.challenge

interface ChallengeService {
    /**
     * Проверяет, является ли правильной попытка пользователя [attemptDTO]
     */
    fun verifyAttempt(attemptDTO: ChallengeAttemptDTO): ChallengeAttempt
}
