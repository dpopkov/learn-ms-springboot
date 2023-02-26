package learn.mssbkt.multiplicationkt.challenge

import learn.mssbkt.multiplicationkt.user.User
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ExtendWith(SpringExtension::class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController::class)
class ChallengeAttemptControllerTest {
    @MockBean
    private lateinit var challengeService: ChallengeService

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var jsonRequestAttempt: JacksonTester<ChallengeAttemptDTO>

    @Autowired
    private lateinit var jsonResultAttempt: JacksonTester<ChallengeAttempt>

    @Test
    fun `post valid attempt - correct result`() {
        // given
        val user = User(id = 1L, alias = "john")
        val attemptId = 5L
        val attemptDTO = ChallengeAttemptDTO(factorA = 50, factorB = 70, userAlias = "john", guess = 3500)
        val expectedResponse =
            ChallengeAttempt(attemptId, user, factorA = 50, factorB = 70, resultAttempt = 3500, correct = true)
        given(
            challengeService.verifyAttempt(attemptDTO)
        )
            .willReturn(expectedResponse)
        // when
        val response: MockHttpServletResponse = mvc.perform(
            post("/attempts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(attemptDTO).json)
        ).andReturn().response
        // then
        then(response.status).isEqualTo(HttpStatus.OK.value())
        then(response.contentAsString).isEqualTo(jsonResultAttempt.write(expectedResponse).json)
    }

    @Test
    fun `post invalid attempt`() {
        // given
        val attemptDTO = ChallengeAttemptDTO(factorA = 2000, factorB = -70, userAlias = "john", guess = 1)
        // when
        val response: MockHttpServletResponse = mvc.perform(
            post("/attempts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(attemptDTO).json)
        ).andReturn().response
        // then
        then(response.status).isEqualTo(HttpStatus.BAD_REQUEST.value())
    }
}
