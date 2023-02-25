package challenge

import learn.mssbkt.multiplicationkt.challenge.Challenge
import learn.mssbkt.multiplicationkt.challenge.ChallengeGeneratorService
import learn.mssbkt.multiplicationkt.challenge.ChallengeGeneratorServiceImpl
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.random.Random

@ExtendWith(MockitoExtension::class)
class ChallengeGeneratorServiceImplTest {

    private lateinit var challengeGeneratorService: ChallengeGeneratorService

    @Mock
    private lateinit var random: Random

    @BeforeEach
    fun setUp() {
        challengeGeneratorService = ChallengeGeneratorServiceImpl(random)
    }

    @Test
    fun `generated random factor is between expected limits`() {
        // given
        val maxToMinRange = 89
        given(random.nextInt(maxToMinRange)).willReturn(20, 30)
        // when
        val challenge = challengeGeneratorService.randomChallenge()
        // then
        then(challenge).isEqualTo(Challenge(factorA = 31, factorB = 41))
    }
}
