package vs.chanban.domain.topic.dto

import jakarta.validation.constraints.NotBlank
import vs.chanban.common.Message.Validation.CANNOT_BE_BLANK

class AddTopicRequestDto(
    @field:NotBlank(message = CANNOT_BE_BLANK)
    val topicTitle: String,
    @field:NotBlank(message = CANNOT_BE_BLANK)
    val topicSubject: String,
    val topicContent: String,
    // Retrieve ip address from request header
    var userIp: String = ""
)