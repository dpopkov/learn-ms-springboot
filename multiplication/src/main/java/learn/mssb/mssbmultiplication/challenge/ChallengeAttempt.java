package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import lombok.Value;

@Value
public class ChallengeAttempt {
    Long id;
    User user;
    int factorA;
    int factorB;
    int resultAttempt;
    boolean correct;
}
