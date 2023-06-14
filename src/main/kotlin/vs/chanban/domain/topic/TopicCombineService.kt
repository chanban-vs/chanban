package vs.chanban.domain.topic

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.topic.dto.AddTopicResponseDto
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto
import vs.chanban.domain.topic.dto.TopicResponseDto

@Service
class TopicCombineService(
    private val topicService: TopicService,
    private val paginationConfig: PaginationConfig
) {
    @Transactional
    fun addTopic(addTopicRequestDto: AddTopicRequestDto): AddTopicResponseDto {
        val topic = topicService.addTopic(Topic.of(addTopicRequestDto))
        return AddTopicResponseDto.of(topic)
    }

    fun getTopic(topicId: Long): TopicResponseDto {
        val topic: Topic = topicService.getTopic(topicId)

        return TopicResponseDto.of(topic)
    }

    // Get topic page by topic subject
    fun getTopicPage(topicSubject: TopicSubject, page: Int?, pageSize: Int?): Page<TopicPreviewResponseDto> {
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val topicPage: Page<Topic> = topicService.getTopicsByTopicSubject(topicSubject, PageRequest.of(currentPage, currentPageSize))

        return topicPage.map { topic ->
            TopicPreviewResponseDto.of(topic)
        }
    }
}