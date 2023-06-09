package vs.chanban.domain.topic

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.domain.poll.PollService
import vs.chanban.domain.topic.dto.AddTopicRequestDto
import vs.chanban.domain.topic.dto.AddTopicResponseDto
import vs.chanban.domain.topic.dto.TopicResponseDto
import vs.chanban.domain.user.User

@Service
class TopicCombineService(
    private val topicService: TopicService,
    private val pollService: PollService
) {
    @Transactional
    fun addTopic(addTopicRequestDto: AddTopicRequestDto): AddTopicResponseDto {
        val topic = topicService.addTopic(Topic.of(addTopicRequestDto))
        return AddTopicResponseDto.of(topic)
    }

    fun getTopic(topicId: Long, user: User): TopicResponseDto {
        val topic: Topic = topicService.getTopicByTopicId(topicId)
        val isPolled: Boolean = pollService.getIsPolled(user)

        return TopicResponseDto.of(topic, isPolled)
    }
}