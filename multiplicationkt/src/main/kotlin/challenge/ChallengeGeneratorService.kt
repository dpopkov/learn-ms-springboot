package learn.mssbkt.multiplicationkt.challenge

interface ChallengeGeneratorService {
    /**
     * Возвращает случайно сгенерированное задание с множителями.
     */
    fun randomChallenge(): Challenge
}
