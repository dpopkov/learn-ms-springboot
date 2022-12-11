package learn.mssb.mssbmultiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static learn.mssb.mssbmultiplication.challenge.ChallengeGeneratorService.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChallengeGeneratorServiceImplTest {

    private static final int BOUND_OF_RANDOM = MAXIMUM_FACTOR + 1 - MINIMUM_FACTOR;

    private ChallengeGeneratorService challengeGeneratorService;

    @Spy
    private Random random;

    @BeforeEach
    void setUp() {
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    void randomChallenge() {
        final int randomForA = 20;
        final int randomForB = 30;
        given(random.nextInt(BOUND_OF_RANDOM)).willReturn(randomForA, randomForB);

        final Challenge challenge = challengeGeneratorService.randomChallenge();

        int expectedA = MINIMUM_FACTOR + randomForA;
        int expectedB = MINIMUM_FACTOR + randomForB;
        assertThat(challenge).isEqualTo(new Challenge(expectedA, expectedB));
    }
}
