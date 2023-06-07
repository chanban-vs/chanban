package vs.chanban.domain.token.dto

class TokenResponseDto(
    val token: String
) {
    companion object {
        fun of(token: String): TokenResponseDto = TokenResponseDto(
            token = token
        )
    }
}