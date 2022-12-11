package learn.mssb.mssbmultiplication.challenge;

public interface ChallengeGeneratorService {
    int MINIMUM_FACTOR = 11;
    int MAXIMUM_FACTOR = 99;

    /** Randomly generates a challenge with factors between 11 and 99. */
    Challenge randomChallenge();
}
