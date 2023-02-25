package challenge

import learn.mssbkt.multiplicationkt.challenge.ChallengeAttemptDTO
import learn.mssbkt.multiplicationkt.challenge.ChallengeService
import learn.mssbkt.multiplicationkt.challenge.ChallengeServiceImpl
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ChallengeServiceImplTest {
    private lateinit var challengeService: ChallengeService

    @BeforeEach
    fun setUp() {
        challengeService = ChallengeServiceImpl()
    }

    @Test
    fun `check correct attempt`() {
        // given
        val attemptDto = ChallengeAttemptDTO(
            factorA = 50,
            factorB = 60,
            userAlias = "john_doe",
            guess = 3000,
        )
        // when
        val resultAttempt = challengeService.verifyAttempt(attemptDto)
        // then
        then(resultAttempt.correct).isTrue()
    }

    @Test
    fun `check wrong attempt`() {
        // given
        val attemptDto = ChallengeAttemptDTO(
            factorA = 50,
            factorB = 60,
            userAlias = "john_doe",
            guess = 5000,
        )
        // when
        val resultAttempt = challengeService.verifyAttempt(attemptDto)
        // then
        then(resultAttempt.correct).isFalse()
    }
}
