package vs.chanban.domain.poll

import vs.chanban.testdata.TestData.Poll.TEST_POLL
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PollRepositoryTest(@Autowired val pollRepository: PollRepository) {
    @Test
    @DisplayName("save poll")
    fun savePoll() {
        val savedPoll = pollRepository.save(TEST_POLL)

        Assertions.assertEquals(TEST_POLL.topicId, savedPoll.topicId)
        Assertions.assertEquals(TEST_POLL.pollAnswer, savedPoll.pollAnswer)
        Assertions.assertEquals(TEST_POLL.userIp, savedPoll.userIp)
    }

}