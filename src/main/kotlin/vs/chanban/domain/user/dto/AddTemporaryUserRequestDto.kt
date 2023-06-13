package vs.chanban.domain.user.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import vs.chanban.common.Message
import vs.chanban.domain.enum.account.role.AccountRole

class AddTemporaryUserRequestDto(
    @field:NotBlank(message = Message.Validation.CANNOT_BE_BLANK)
    val email: String? = null,
    @field:NotNull(message = Message.Validation.CANNOT_BE_NULL)
    var password: String? = null,

    @field:JsonIgnore
    var roles: MutableSet<AccountRole> = mutableSetOf(),
    @field:JsonIgnore
    var verificationCode: String = ""
) {
    fun setDefaultRoles() {
        this.roles = mutableSetOf(AccountRole.USER)
    }
}