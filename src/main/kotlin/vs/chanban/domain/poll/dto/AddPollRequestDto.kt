package vs.chanban.domain.poll.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message.Validation.CANNOT_BE_BLANK
import vs.chanban.common.Message.Validation.CANNOT_BE_NULL
import vs.chanban.domain.topic.Topic
import vs.chanban.domain.user.User

class AddPollRequestDto(
    @field:NotNull(message = CANNOT_BE_NULL)
    val topicId: Long? = null,
    @field:NotBlank(message = CANNOT_BE_BLANK)
    val pollAnswer: String? = null,

    @field:JsonIgnore
    var createdBy: User? = null,
    @field:JsonIgnore
    var topic: Topic? = null
)