package vs.chanban.domain.token

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import vs.chanban.common.Message.Authentication.EMPTY_TOKEN
import vs.chanban.common.Message.Authentication.INVALID_TOKEN
import vs.chanban.common.constant.Constant.Authentication.AUTHORIZATION
import vs.chanban.common.constant.Constant.Authentication.EXPIRES_AFTER
import vs.chanban.common.constant.Constant.Authentication.HS256_ALGORITHM
import vs.chanban.common.constant.Constant.Authentication.ISSUER
import vs.chanban.common.constant.Constant.Authentication.TOKEN_PREFIX
import vs.chanban.common.constant.Constant.Common.USER_ID
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
            val userId: Long = jwt.claims[USER_ID] as Long
            userService.getUserById(userId)
        } catch (ex: JwtException) {
            throw ChanbanBizException(HttpStatus.UNAUTHORIZED, INVALID_TOKEN)
        }
    }

    // get User from jwt
    fun getUserFromToken(httpServletRequest: HttpServletRequest): User {
        val jwt: String
        val bearerToken = httpServletRequest.getHeader(AUTHORIZATION)

        if (bearerToken.isNullOrEmpty()) {
            throw ChanbanBizException(HttpStatus.UNAUTHORIZED, EMPTY_TOKEN)
        }

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            jwt = bearerToken.substring(TOKEN_PREFIX.length)
        } else {
            throw ChanbanBizException(HttpStatus.UNAUTHORIZED, INVALID_TOKEN)
        }

        return parseToken(jwt)
    }
}