package vs.chanban.domain.poll.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message
import vs.chanban.domain.user.User

class UpdatePollRequestDto(
    @field:NotNull(message = Message.Validation.CANNOT_BE_NULL)
    val pollId: Long? = null,
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val pollAnswer: String? = null,

    @field:JsonIgnore
    var createdBy: User? = null
)