package learn.mssb.mssbmultiplication.challenge;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {

    private static final int UPPER_BOUND_FOR_RANDOM = MAXIMUM_FACTOR - MINIMUM_FACTOR + 1;

    private final Random random;

    ChallengeGeneratorServiceImpl() {
        this.random = new Random();
    }

    protected ChallengeGeneratorServiceImpl(Random random) {
        this.random = random;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(nextFactor(), nextFactor());
    }

    private int nextFactor() {
        return MINIMUM_FACTOR + random.nextInt(UPPER_BOUND_FOR_RANDOM);
    }
}
