package vs.chanban.domain.topic.dto

import vs.chanban.domain.enum.dto.ChanbanEnumDto
import vs.chanban.domain.topic.Topic

class TopicResponseDto(
    val topicId: Long,
    val topicTitle: String,
    val topicSubject: ChanbanEnumDto,
    val topicContent: String,
) {
    companion object {
        fun of(topic: Topic): TopicResponseDto = TopicResponseDto(
            topicId = topic.topicId!!,
            topicTitle = topic.topicTitle,
            topicSubject = ChanbanEnumDto.of(topic.topicSubject),
            topicContent = topic.topicContent
        )
    }
}