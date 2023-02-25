package learn.mssbkt.multiplicationkt.challenge

import org.springframework.stereotype.Service
import kotlin.random.Random

private const val MINIMUM_FACTOR = 11
private const val MAXIMUM_FACTOR = 100
private const val MIN_TO_MAX_RANGE = MAXIMUM_FACTOR - MINIMUM_FACTOR

@Service
class ChallengeGeneratorServiceImpl(
    private val random: Random = Random.Default
) : ChallengeGeneratorService {
    override fun randomChallenge(): Challenge {
        return Challenge(factorA = next(), factorB = next())
    }

    private fun next(): Int = random.nextInt(MIN_TO_MAX_RANGE) + MINIMUM_FACTOR
}
