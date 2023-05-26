package vs.chanban.domain.topic.listener

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.topic.TopicRepository
import java.time.LocalDateTime

@SpringBootTest
class BaseTimeListenerTest(@Autowired val topicRepository: TopicRepository) {
    val topic = Topic("test title", "test topic", "test user ip")

    @Test
    @DisplayName("saving createdAt test")
    fun saveCreatedAt() {
        val dateBeforeSave = LocalDateTime.now()
        val createdAt = topicRepository.save(topic).createdAt
        val dateAfterSave = LocalDateTime.now()

        Assertions.assertTrue(createdAt!! > dateBeforeSave)
        Assertions.assertTrue(createdAt < dateAfterSave)
    }
}