package vs.chanban.domain.user.email.verification.dto

class CheckEmailExistenceResponseDto(
    val exists: Boolean
) {
    companion object {
        fun of(exists: Boolean): CheckEmailExistenceResponseDto = CheckEmailExistenceResponseDto(
            exists = exists
        )
    }
}