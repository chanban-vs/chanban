package vs.chanban.domain.user.email.verification.dto

import jakarta.validation.constraints.NotBlank
import vs.chanban.common.Message

class CheckEmailExistenceRequestDto(
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val email: String
)