package learn.mssb.mssbmultiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChallengeServiceImplTest {

    private static final int FACTOR_A = 50;
    private static final int FACTOR_B = 60;
    private static final int GUESS = 3000;
    private static final String USER_ALIAS = "john_doe";

    private ChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    void verifyCorrectAttempt() {
        // Given
        ChallengeAttemptDto dto = new ChallengeAttemptDto(USER_ALIAS, FACTOR_A, FACTOR_B, GUESS);
        // When
        final ChallengeAttempt result = challengeService.verifyAttempt(dto);
        // Then
        assertTrue(result.isCorrect());
    }

    @Test
    void verifyWrongAttempt() {
        // Given
        ChallengeAttemptDto dto = new ChallengeAttemptDto(USER_ALIAS, FACTOR_A, FACTOR_B, GUESS + 1);
        // When
        final ChallengeAttempt result = challengeService.verifyAttempt(dto);
        // Then
        assertFalse(result.isCorrect());
    }
}
