package learn.mssb.mssbmultiplication.challenge;

import learn.mssb.mssbmultiplication.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
class ChallengeAttemptControllerTest {

    private static final String USER_ALIAS = "john_doe";

    @MockBean
    private ChallengeService challengeService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<ChallengeAttemptDto> jsonRequestAttempt;
    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;
    @Autowired
    private JacksonTester<List<ChallengeAttempt>> jsonResultAttemptList;

    @Test
    void postValidResult() throws Exception {
        // Given
        User user = new User(1L, "john");
        Long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto("john", 50, 70, 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user, 50, 70,
                3500, true);
        given(challengeService.verifyAttempt(eq(attemptDto))).willReturn(expectedResponse);
        // When
        MockHttpServletResponse response = mockMvc.perform(post("/attempts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(attemptDto).getJson())
        ).andReturn().getResponse();
        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());
    }

    @Test
    void postInvalidResult() throws Exception {
        // Given
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto("john", 50, -70, 3500);
        // When
        MockHttpServletResponse response = mockMvc.perform(post("/attempts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(attemptDto).getJson())
        ).andReturn().getResponse();
        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getLastAttempts() throws Exception {
        // Given
        User user = new User(1L, USER_ALIAS);
        ChallengeAttempt attempt1 = new ChallengeAttempt(
                2L, user, 10, 20, 200, true);
        ChallengeAttempt attempt2 = new ChallengeAttempt(
                3L, user, 20, 20, 200, false);
        List<ChallengeAttempt> attemptList = List.of(attempt1, attempt2);
        given(challengeService.getStatsForUser(USER_ALIAS)).willReturn(attemptList);
        // When
        MockHttpServletResponse response = mockMvc.perform(get("/attempts").param("alias", USER_ALIAS))
                .andReturn().getResponse();
        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(response.getContentAsString()).isEqualTo(jsonResultAttemptList.write(attemptList).getJson());
    }
}
