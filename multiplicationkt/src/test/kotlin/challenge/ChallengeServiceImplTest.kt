package challenge

import learn.mssbkt.multiplicationkt.challenge.*
import learn.mssbkt.multiplicationkt.user.User
import learn.mssbkt.multiplicationkt.user.UserRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ChallengeServiceImplTest {
    private lateinit var challengeService: ChallengeService
    @Mock
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var attemptRepository: ChallengeAttemptRepository

    @BeforeEach
    fun setUp() {
        challengeService = ChallengeServiceImpl(userRepository, attemptRepository)
        given(attemptRepository.save(any())).will(returnsFirstArg<ChallengeAttempt>())
    }

    @Test
    fun `check correct attempt`() {
        // given
        given(userRepository.save(any())).will(returnsFirstArg<User>())
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
        verify(userRepository).save(User("john_doe"))
        verify(attemptRepository).save(resultAttempt)
    }

    @Test
    fun `check wrong attempt`() {
        // given
        given(userRepository.save(any())).will(returnsFirstArg<User>())
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
        verify(userRepository).save(User("john_doe"))
        verify(attemptRepository).save(resultAttempt)
    }

    @Test
    fun `check existing user`() {
        // given
        val existingUser = User("john_doe")
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(existingUser))
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
        then(resultAttempt.user).isEqualTo(existingUser)
        verify(userRepository, never()).save(any())
        verify(attemptRepository).save(resultAttempt)
    }

    @Test
    fun `retrieve last attempts`() {
        // given
        val existingUser = User("john_doe", 1L)
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(existingUser))
        given(attemptRepository.lastAttempts("john_doe")).willReturn(listOf(
            ChallengeAttempt(existingUser, 50, 60, 5000, false),
            ChallengeAttempt(existingUser, 50, 60, 3000, true),
        ))
        val dto1 = ChallengeAttemptDTO(
            factorA = 50,
            factorB = 60,
            userAlias = "john_doe",
            guess = 5000,
        )
        val dto2 = dto1.copy(guess = 3000)
        // when
        val resultAttempt1 = challengeService.verifyAttempt(dto1)
        val resultAttempt2 = challengeService.verifyAttempt(dto2)
        val attempts: List<ChallengeAttempt> = challengeService.getStatsForUser("john_doe")
        // then
        then(attempts).hasSize(2)
        then(attempts).contains(resultAttempt1)
        then(attempts).contains(resultAttempt2)
        verify(attemptRepository).lastAttempts("john_doe")
    }
}
