package vs.chanban.domain.user.email.verification

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import vs.chanban.common.constant.Constant.TemporaryUser.TIME_TO_LIVE
import vs.chanban.domain.enum.account.role.AccountRole
import vs.chanban.domain.user.dto.AddTemporaryUserRequestDto

@RedisHash(value = "temporary_user", timeToLive = TIME_TO_LIVE)
data class TemporaryUser(
    @Id
    val email: String,
    val password: String,
    val roles: MutableSet<AccountRole>,
    val verificationCode: String
) {
    companion object {
        fun of(
            addTemporaryUserRequestDto: AddTemporaryUserRequestDto
        ): TemporaryUser = TemporaryUser(
            email = addTemporaryUserRequestDto.email!!,
            password = addTemporaryUserRequestDto.password!!,
            roles = addTemporaryUserRequestDto.roles,
            verificationCode = addTemporaryUserRequestDto.verificationCode
        )
    }
}