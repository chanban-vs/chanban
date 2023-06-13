package vs.chanban.domain.topic

import vs.chanban.testdata.TestData.Topic.SPORTS_TOPIC
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import vs.chanban.common.Message.Topic.TOPIC_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import java.util.*

@SpringBootTest
class TopicServiceTest {
    @Mock
    private lateinit var topicRepository: TopicRepository
    private lateinit var topicService: TopicService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        topicService = TopicService(topicRepository)
    }

    @Test
    @DisplayName("get existing topic by topic id")
    fun getExistingTopicById() {
        `when`(topicRepository.findById(SPORTS_TOPIC.topicId!!)).thenReturn(Optional.of(SPORTS_TOPIC))

        val topic: Topic = topicService.getTopicByTopicId(SPORTS_TOPIC.topicId!!)

        Assertions.assertEquals(topic, SPORTS_TOPIC)
    }

    @Test
    @DisplayName("get non-existing topic by topic id")
    fun getNonExistingTopicById() {
        val testTopicId: Long = SPORTS_TOPIC.topicId!!
        `when`(topicRepository.findById(testTopicId)).thenReturn(Optional.empty())

        val exception = Assertions.assertThrows(ChanbanBizException::class.java) {
            topicService.getTopicByTopicId(testTopicId)
        }

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.httpStatus)
        Assertions.assertEquals(TOPIC_NOT_FOUND.format(testTopicId), exception.message)
    }

}