package vs.chanban.domain.topic.dto

import vs.chanban.domain.enum.dto.ChanbanEnumDto
import java.time.LocalDateTime

class TopicPreviewResponseDto(
    val topicId: Long?,
    val topicTitle: String?,
    val topicSubject: ChanbanEnumDto,
    val createdAt: LocalDateTime?
)