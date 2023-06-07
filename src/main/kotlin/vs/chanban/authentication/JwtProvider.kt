package vs.chanban.authentication

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.stereotype.Component
import vs.chanban.common.constant.Constant.Authentication.AES_ALGORITHM
import vs.chanban.common.constant.Constant.Authentication.SECRET_KEY_BITS
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@Component
class JwtProvider {
    val secretKey: SecretKey = generateSecretKey()

    private fun generateSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM)
        keyGenerator.init(SECRET_KEY_BITS)
        return keyGenerator.generateKey()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withSecretKey(secretKey).build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}