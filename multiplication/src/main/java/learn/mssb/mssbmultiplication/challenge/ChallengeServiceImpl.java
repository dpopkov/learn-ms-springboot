package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto attempt) {
        boolean isCorrect = attempt.getFactorA() * attempt.getFactorB() == attempt.getGuess();
        User user = new User(null, attempt.getUserAlias());
        return new ChallengeAttempt(null, user,
                attempt.getFactorA(), attempt.getFactorB(),
                attempt.getGuess(), isCorrect);
    }
}
