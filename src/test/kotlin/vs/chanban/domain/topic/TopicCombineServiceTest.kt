package vs.chanban.domain.topic

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
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.testdata.TestData.Topic.ANOTHER_SPORTS_TOPIC
import vs.chanban.testdata.TestData.Topic.SPORTS_TOPIC

@SpringBootTest
class TopicCombineServiceTest {
    @Mock
    private lateinit var topicService: TopicService
    @Mock
    private lateinit var paginationConfig: PaginationConfig
    private lateinit var topicCombineService: TopicCombineService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        topicCombineService = TopicCombineService(topicService, paginationConfig)
    }

    @Test
    @DisplayName("get topic page by subject")
    fun getTopicPage() {
        val topicSubject = SPORTS_TOPIC.topicSubject
        val page = 1
        val pageSize = 10
        val topics = listOf(SPORTS_TOPIC, ANOTHER_SPORTS_TOPIC)
        val pageRequest = PageRequest.of(page, pageSize)
        val topicPage = PageImpl(topics, pageRequest, topics.size.toLong())

        `when`(topicService.getTopicsByTopicSubject(topicSubject, pageRequest)).thenReturn(topicPage)

        val result: Page<TopicPreviewResponseDto> = topicCombineService.getTopicPage(topicSubject, page, pageSize)

        Assertions.assertEquals(topics.size, result.content.size)
        Assertions.assertEquals(topics[0].topicId, result.content[0].topicId)
        Assertions.assertEquals(topics[0].topicTitle, result.content[0].topicTitle)
        Assertions.assertEquals(topics[0].topicSubject.name, result.content[0].topicSubject.name)
        Assertions.assertEquals(topics[0].createdAt, result.content[0].createdAt)

        Assertions.assertEquals(topics[1].topicId, result.content[1].topicId)
        Assertions.assertEquals(topics[1].topicTitle, result.content[1].topicTitle)
        Assertions.assertEquals(topics[1].topicSubject.name, result.content[1].topicSubject.name)
        Assertions.assertEquals(topics[1].createdAt, result.content[1].createdAt)
    }

}