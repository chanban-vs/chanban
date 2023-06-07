package vs.chanban.domain.token

import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import vs.chanban.common.Message.Authentication.UNAUTHORIZED
import vs.chanban.common.constant.Constant.Authentication.EXPIRES_AFTER
import vs.chanban.common.constant.Constant.Authentication.HS256_ALGORITHM
import vs.chanban.common.constant.Constant.Authentication.ISSUER
import vs.chanban.common.constant.Constant.User.USER_ID
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.user.User
import vs.chanban.domain.user.UserService
import java.time.Instant

@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService
) {
    fun generateToken(user: User): String {
        val jwsHeader = JwsHeader.with { HS256_ALGORITHM }.build()
        val claims = JwtClaimsSet.builder()
            .issuer(ISSUER)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusMillis(EXPIRES_AFTER))
            .claim(USER_ID, user.userId)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims[USER_ID] as Long
            userService.getUserById(userId)
        } catch (ex: JwtException) {
            throw ChanbanBizException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED)
        }
    }
}