package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "challenge_attempt")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
