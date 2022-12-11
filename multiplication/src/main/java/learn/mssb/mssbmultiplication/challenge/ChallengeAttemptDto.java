package learn.mssb.mssbmultiplication.challenge;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Value
public class ChallengeAttemptDto {
    @NotBlank
    String userAlias;
    @Min(1) @Max(99)
    int factorA;
    @Min(1) @Max(99)
    int factorB;
    @Positive(message = "You can not get a negative result here. Try again")
    int guess;
}
