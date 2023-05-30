package vs.chanban.domain.topic

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Topic.TOPIC_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.configuration.PaginationConfig
import vs.chanban.domain.enum.dto.ChanbanEnumDto
import vs.chanban.domain.enum.topic.TopicSubject
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.topic.dto.TopicPreviewResponseDto

@Service
@Transactional
class TopicService(
        private val topicRepository: TopicRepository,
        private val paginationConfig: PaginationConfig
) {
    fun addTopic(addTopicRequestDto: AddTopicRequestDto): Topic {
        return topicRepository.save(Topic.of(addTopicRequestDto))
    }

    // Get topic by topic id
    @Transactional(readOnly = true)
    fun getTopicById(topicId: Long): Topic {
        return topicRepository.findById(topicId).orElseThrow {
                ChanbanBizException(HttpStatus.NOT_FOUND, TOPIC_NOT_FOUND.format(topicId))
            }
    }

    // Get topic page by topic subject
    @Transactional(readOnly = true)
    fun getTopicsByTopicSubject(topicSubject: TopicSubject, page: Int?, pageSize: Int?): Page<TopicPreviewResponseDto> {
        val currentPage = page ?: paginationConfig.defaultPage
        val currentPageSize = pageSize ?: paginationConfig.defaultPageSize

        val topicPage: Page<Topic> = topicRepository.findAllByTopicSubject(topicSubject, PageRequest.of(currentPage, currentPageSize))

        val topicDtoPage: Page<TopicPreviewResponseDto> = topicPage.map { topic ->
            // Perform the mapping from Topic to TopicDto here
            TopicPreviewResponseDto(
                topicId = topic.topicId,
                topicTitle = topic.topicTitle,
                topicSubject = ChanbanEnumDto.of(topic.topicSubject),
                createdAt = topic.createdAt
            )
        }

        return topicDtoPage
    }

}