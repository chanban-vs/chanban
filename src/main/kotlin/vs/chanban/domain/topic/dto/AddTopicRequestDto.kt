package vs.chanban.domain.topic.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import vs.chanban.common.Message.Validation.CANNOT_BE_BLANK
import vs.chanban.domain.user.User

class AddTopicRequestDto(
    @field:NotBlank(message = CANNOT_BE_BLANK)
    val topicTitle: String,
    @field:NotBlank(message = CANNOT_BE_BLANK)
    val topicSubject: String,
    val topicContent: String,

    @field:JsonIgnore
    var createdBy: User? = null
)