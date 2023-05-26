package vs.chanban.domain.topic

import TestData.Topic.TEST_TOPIC
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TopicRepositoryTest(@Autowired val topicRepository: TopicRepository) {
    @Test
    @DisplayName("save topic")
    fun saveTopic() {
        val savedTopic = topicRepository.save(TEST_TOPIC)

        Assertions.assertEquals(TEST_TOPIC.topicTitle, savedTopic.topicTitle)
        Assertions.assertEquals(TEST_TOPIC.topicSubject, savedTopic.topicSubject)
        Assertions.assertEquals(TEST_TOPIC.topicContent, savedTopic.topicContent)
        Assertions.assertEquals(TEST_TOPIC.userIp, savedTopic.userIp)
    }
}