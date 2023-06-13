package vs.chanban.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message

class LoginRequestDto(
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val email: String? = null,
    @field:NotNull(message = Message.Validation.CANNOT_BE_NULL)
    val password: String? = null
)