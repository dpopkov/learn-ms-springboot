package learn.mssb.mssbmultiplication.challenge;

import java.util.List;

public interface ChallengeService {

    ChallengeAttempt verifyAttempt(ChallengeAttemptDto attempt);

    List<ChallengeAttempt> getStatsForUser(String userAlias);
}
