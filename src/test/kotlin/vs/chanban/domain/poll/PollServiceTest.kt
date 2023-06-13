package vs.chanban.domain.poll

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.testdata.TestData.Topic.SPORTS_TOPIC

@SpringBootTest
class PollServiceTest {
    @Mock
    private lateinit var pollRepository: PollRepository
    private lateinit var pollService: PollService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        pollService = PollService(pollRepository)
    }

    @Test
    @DisplayName("count positive polls")
    fun countPositivePolls() {
        val pollAnswer = PollOption.POSITIVE
        val numberOfPositivePolls = 1L
        `when`(pollRepository.countByTopicIdAndPollAnswer(SPORTS_TOPIC, pollAnswer)).thenReturn(numberOfPositivePolls)

        val polls: Long = pollService.getPollResult(SPORTS_TOPIC, pollAnswer)

        Assertions.assertEquals(numberOfPositivePolls, polls)
    }
}