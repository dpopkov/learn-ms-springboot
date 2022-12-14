package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import learn.mssb.mssbmultiplication.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository challengeAttemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto attempt) {
        User user = userRepository.findByAlias(attempt.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", attempt.getUserAlias());
                    return userRepository.save(new User(null, attempt.getUserAlias()));
                });
        boolean isCorrect = attempt.getFactorA() * attempt.getFactorB() == attempt.getGuess();
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(null, user,
                attempt.getFactorA(), attempt.getFactorB(),
                attempt.getGuess(), isCorrect);
        challengeAttemptRepository.save(challengeAttempt);
        return challengeAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(String userAlias) {
        return challengeAttemptRepository.lastAttempts(userAlias);
    }
}
