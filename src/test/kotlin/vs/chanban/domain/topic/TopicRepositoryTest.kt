package vs.chanban.domain.topic

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TopicRepositoryTest(@Autowired val topicRepository: TopicRepository) {
    val testTopic = Topic("test title", "test topic", "test user ip")

    @Test
    @DisplayName("topic saving test")
    fun saveTopic() {
        val savedTopic = topicRepository.save(testTopic)

        Assertions.assertEquals(testTopic.topicTitle, savedTopic.topicTitle)
        Assertions.assertEquals(testTopic.topicContent, savedTopic.topicContent)
        Assertions.assertEquals(testTopic.userIp, savedTopic.userIp)
    }
}