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
class TopicService(private val topicRepository: TopicRepository) {
}