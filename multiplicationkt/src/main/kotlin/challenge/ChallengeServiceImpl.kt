package learn.mssbkt.multiplicationkt.challenge

import learn.mssbkt.multiplicationkt.user.User
import org.springframework.stereotype.Service

@Service
class ChallengeServiceImpl : ChallengeService {
    override fun verifyAttempt(attemptDTO: ChallengeAttemptDTO): ChallengeAttempt {
        val isCorrect = (attemptDTO.factorA * attemptDTO.factorB) == attemptDTO.guess
        val user = User(
            id = -1,    // пока не используем id
            alias = attemptDTO.userAlias,
        )
        return ChallengeAttempt(
            id = -1L,   // пока не используем id
            user = user,
            factorA = attemptDTO.factorA,
            factorB = attemptDTO.factorB,
            resultAttempt = attemptDTO.guess,
            correct = isCorrect,
        )
    }
}
