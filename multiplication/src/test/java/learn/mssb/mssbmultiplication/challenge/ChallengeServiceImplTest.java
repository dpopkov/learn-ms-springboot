package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import learn.mssb.mssbmultiplication.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceImplTest {

    private static final int FACTOR_A = 50;
    private static final int FACTOR_B = 60;
    private static final int GUESS = 3000;
    private static final String USER_ALIAS = "john_doe";

    @Mock
    private UserRepository userRepository;
    @Mock
    private ChallengeAttemptRepository challengeAttemptRepository;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    private ChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeServiceImpl(
                userRepository,
                challengeAttemptRepository
        );
    }

    @Test
    void verifyCorrectAttempt() {
        // Given
        ChallengeAttemptDto dto = new ChallengeAttemptDto(USER_ALIAS, FACTOR_A, FACTOR_B, GUESS);
        given(challengeAttemptRepository.save(ArgumentMatchers.any())).willReturn(new ChallengeAttempt());
        // When
        final ChallengeAttempt result = challengeService.verifyAttempt(dto);
        // Then
        assertTrue(result.isCorrect());
        then(userRepository).should().save(userCaptor.capture());
        assertEquals(USER_ALIAS, userCaptor.getValue().getAlias());
        then(challengeAttemptRepository).should().save(result);
    }

    @Test
    void verifyCorrectAttemptOfExistingUser() {
        // Given
        User existingUser = new User(1L, USER_ALIAS);
        given(userRepository.findByAlias(USER_ALIAS)).willReturn(Optional.of(existingUser));
        ChallengeAttemptDto dto = new ChallengeAttemptDto(USER_ALIAS, FACTOR_A, FACTOR_B, GUESS);
        given(challengeAttemptRepository.save(ArgumentMatchers.any())).willReturn(new ChallengeAttempt());
        // When
        final ChallengeAttempt result = challengeService.verifyAttempt(dto);
        // Then
        assertTrue(result.isCorrect());
        then(userRepository).should().findByAlias(anyString());
        then(userRepository).shouldHaveNoMoreInteractions();
        then(challengeAttemptRepository).should().save(result);
    }

    @Test
    void verifyWrongAttempt() {
        // Given
        ChallengeAttemptDto dto = new ChallengeAttemptDto(USER_ALIAS, FACTOR_A, FACTOR_B, GUESS + 1);
        given(challengeAttemptRepository.save(ArgumentMatchers.any())).willReturn(new ChallengeAttempt());
        // When
        final ChallengeAttempt result = challengeService.verifyAttempt(dto);
        // Then
        assertFalse(result.isCorrect());
        then(userRepository).should().save(userCaptor.capture());
        assertEquals(USER_ALIAS, userCaptor.getValue().getAlias());
        then(challengeAttemptRepository).should().save(result);
    }

    @Test
    void getStatsForUser() {
        // Given
        given(challengeAttemptRepository.lastAttempts(anyString())).willReturn(List.of(new ChallengeAttempt()));
        // When
        final List<ChallengeAttempt> attempts = challengeService.getStatsForUser(USER_ALIAS);
        // Then
        then(challengeAttemptRepository).should().lastAttempts(USER_ALIAS);
        assertEquals(1, attempts.size());
    }
}
