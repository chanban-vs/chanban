package vs.chanban.domain.topic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.Topic.TOPIC_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.enum.topic.TopicSubject

@Service
@Transactional
class TopicService(
        private val topicRepository: TopicRepository
) {
    fun addTopic(topic: Topic): Topic {
        return topicRepository.save(topic)
    }

    // Get topic by topic id
    @Transactional(readOnly = true)
    fun getTopicByTopicId(topicId: Long): Topic {
        return topicRepository.findById(topicId).orElseThrow {
                ChanbanBizException(HttpStatus.NOT_FOUND, TOPIC_NOT_FOUND.format(topicId))
            }
    }

    // Get topic page by topic subject
    @Transactional(readOnly = true)
    fun getTopicsByTopicSubject(topicSubject: TopicSubject, pageable: Pageable): Page<Topic> {
        return topicRepository.findAllByTopicSubjectOrderByCreatedAtDesc(topicSubject, pageable)
    }
}