package learn.mssb.mssbmultiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ChallengeAttempt> verifyAttempt(@RequestBody @Valid ChallengeAttemptDto challengeAttemptDto) {
        ChallengeAttempt verifiedAttempt = challengeService.verifyAttempt(challengeAttemptDto);
        log.info("Verified attempt: {}", verifiedAttempt);
        return ResponseEntity.ok(verifiedAttempt);
    }
}
