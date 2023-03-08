package learn.mssbkt.multiplicationkt.challenge

import learn.mssbkt.multiplicationkt.user.User
import learn.mssbkt.multiplicationkt.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChallengeServiceImpl(
    private val userRepository: UserRepository,
    private val challengeAttemptRepository: ChallengeAttemptRepository
) : ChallengeService {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun verifyAttempt(attemptDTO: ChallengeAttemptDTO): ChallengeAttempt {
        val isCorrect = (attemptDTO.factorA * attemptDTO.factorB) == attemptDTO.guess
        val user: User = userRepository.findByAlias(attemptDTO.userAlias)
            .orElseGet {
                log.info("Creating new user with alias '{}'", attemptDTO.userAlias)
                userRepository.save(User(attemptDTO.userAlias))
            }
        val checkedAttempt = ChallengeAttempt(
            user = user,
            factorA = attemptDTO.factorA,
            factorB = attemptDTO.factorB,
            resultAttempt = attemptDTO.guess,
            correct = isCorrect,
        )
        val storedAttempt = challengeAttemptRepository.save(checkedAttempt)
        return storedAttempt
    }

    override fun getStatsForUser(alias: String): List<ChallengeAttempt> {
        return challengeAttemptRepository.lastAttempts(alias)
    }
}
