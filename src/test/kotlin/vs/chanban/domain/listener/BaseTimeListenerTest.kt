package vs.chanban.domain.listener

import TestData.Topic.TEST_TOPIC
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import vs.chanban.domain.topic.TopicRepository
import java.time.LocalDateTime

@SpringBootTest
class BaseTimeListenerTest(@Autowired val topicRepository: TopicRepository) {
    @Test
    @DisplayName("save created at")
    fun saveCreatedAt() {
        val dateBeforeSave = LocalDateTime.now()
        val savedCreatedAt = topicRepository.save(TEST_TOPIC).createdAt
        val dateAfterSave = LocalDateTime.now()

        Assertions.assertTrue(savedCreatedAt!! > dateBeforeSave)
        Assertions.assertTrue(savedCreatedAt < dateAfterSave)
    }
}