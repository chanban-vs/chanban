package vs.chanban.domain.topic

import vs.chanban.testdata.TestData.Topic.NEW_TOPIC
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
        val savedTopic = topicRepository.save(NEW_TOPIC)

        Assertions.assertEquals(NEW_TOPIC.topicTitle, savedTopic.topicTitle)
        Assertions.assertEquals(NEW_TOPIC.topicSubject, savedTopic.topicSubject)
        Assertions.assertEquals(NEW_TOPIC.topicContent, savedTopic.topicContent)
        Assertions.assertEquals(NEW_TOPIC.userIp, savedTopic.userIp)
    }
}