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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import vs.chanban.common.Message.Topic.TOPIC_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.testdata.TestData.Topic.ANOTHER_SPORTS_TOPIC
import java.util.*

@SpringBootTest
class TopicServiceTest {
    @Mock
    private lateinit var topicRepository: TopicRepository
    @Mock
    private lateinit var paginationConfig: PaginationConfig
    private lateinit var topicService: TopicService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        paginationConfig = PaginationConfig()
        paginationConfig.defaultPage = 0
        paginationConfig.defaultPageSize = 10
        topicService = TopicService(topicRepository, paginationConfig)
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

    @Test
    @DisplayName("get topics as page by topic subject")
    fun testGetTopicsByTopicSubject() {
        val page = 0
        val pageSize = 10
        val topics = listOf(SPORTS_TOPIC, ANOTHER_SPORTS_TOPIC)
        val pageable: Pageable = PageRequest.of(page, pageSize)
        val topicPage: Page<Topic> = PageImpl(topics, pageable, topics.size.toLong())

        `when`(topicRepository.findAllByTopicSubject(SPORTS_TOPIC.topicSubject, pageable)).thenReturn(topicPage)

        val result: Page<TopicPreviewResponseDto> = topicService.getTopicsByTopicSubject(SPORTS_TOPIC.topicSubject, page, pageSize)

        Assertions.assertEquals(topicPage.totalElements, result.totalElements)
        Assertions.assertEquals(topicPage.content.size, result.content.size)

        val topic1 = result.content[0]
        Assertions.assertEquals(SPORTS_TOPIC.topicId, topic1.topicId)
        Assertions.assertEquals(SPORTS_TOPIC.topicTitle, topic1.topicTitle)
        Assertions.assertEquals(SPORTS_TOPIC.createdAt, topic1.createdAt)

        val topic2 = result.content[1]
        Assertions.assertEquals(ANOTHER_SPORTS_TOPIC.topicId, topic2.topicId)
        Assertions.assertEquals(ANOTHER_SPORTS_TOPIC.topicTitle, topic2.topicTitle)
        Assertions.assertEquals(ANOTHER_SPORTS_TOPIC.createdAt, topic2.createdAt)
    }
}