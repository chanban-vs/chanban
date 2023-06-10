package vs.chanban.domain.user.dto

import jakarta.validation.constraints.NotBlank
import vs.chanban.common.Message

class LoginRequestDto(
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val email: String? = null,
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val password: String? = null
)