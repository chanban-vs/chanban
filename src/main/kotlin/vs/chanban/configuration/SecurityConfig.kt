package vs.chanban.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.ErrorResponse
import vs.chanban.domain.token.TokenService
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import vs.chanban.common.Message.Authentication.UNAUTHORIZED
import vs.chanban.common.error.ChanbanErrorResponse
import vs.chanban.domain.enum.account.role.AccountRole

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val tokenService: TokenService,
    private val objectMapper: ObjectMapper
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    val unauthorizedEntryPoint: AuthenticationEntryPoint = AuthenticationEntryPoint { _, response, _ ->
        val httpStatus = HttpStatus.UNAUTHORIZED
        val fail: ChanbanErrorResponse = ChanbanErrorResponse(httpStatus.value(), UNAUTHORIZED)
        response.status = httpStatus.value()
        val json = objectMapper.writeValueAsString(fail)
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val writer = response.writer
        writer.write(json)
        writer.flush()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        // Define public and private routes
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
            .requestMatchers("/**").authenticated()
            .anyRequest().permitAll()

        // Configure JWT
        http.oauth2ResourceServer().jwt()
        http.authenticationManager { auth ->
            val jwt = auth as BearerTokenAuthenticationToken
            val user = tokenService.parseToken(jwt.token)/** ?: throw ChanbanBizException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED) **/
            UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority(AccountRole.USER.name)))
        }

        // Other configuration
        http.cors()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.csrf().disable()
        http.headers().frameOptions().disable()
        http.headers().xssProtection().disable()

        http.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        // allow localhost for dev purposes
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}