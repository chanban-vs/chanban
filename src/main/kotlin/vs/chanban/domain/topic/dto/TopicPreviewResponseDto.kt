package vs.chanban.domain.topic.dto

import vs.chanban.domain.enum.dto.ChanbanEnumDto
import vs.chanban.domain.topic.Topic
import java.time.LocalDateTime

class TopicPreviewResponseDto(
    val topicId: Long,
    val topicTitle: String,
    val topicSubject: ChanbanEnumDto,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun of(topic: Topic): TopicPreviewResponseDto = TopicPreviewResponseDto(
            topicId = topic.topicId!!,
            topicTitle = topic.topicTitle,
            topicSubject = ChanbanEnumDto.of(topic.topicSubject),
            createdAt = topic.createdAt
        )
    }
}