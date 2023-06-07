package vs.chanban.controller.user

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vs.chanban.domain.token.dto.TokenResponseDto
import vs.chanban.domain.user.UserCombineService
import vs.chanban.domain.user.dto.AddTemporaryUserRequestDto
import vs.chanban.domain.user.dto.LoginRequestDto
import vs.chanban.domain.user.email.verification.dto.CheckEmailExistenceRequestDto
import vs.chanban.domain.user.email.verification.dto.CheckEmailExistenceResponseDto
import vs.chanban.domain.user.email.verification.dto.VerifyEmailRequestDto

@RestController
@RequestMapping("/user")
class UserController(
    private val userCombineService: UserCombineService
) {
    @GetMapping("/email")
    fun getEmailExistence(@RequestBody @Valid checkEmailExistenceRequestDto: CheckEmailExistenceRequestDto): ResponseEntity<CheckEmailExistenceResponseDto> {
        return ResponseEntity.ok(userCombineService.checkEmailExistence(checkEmailExistenceRequestDto))
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequestDto: LoginRequestDto): ResponseEntity<TokenResponseDto> {
        return ResponseEntity.ok(userCombineService.login(loginRequestDto))
    }

    @PostMapping("/temporal")
    fun addTemporaryUSer(@RequestBody @Valid addTemporaryUserRequestDto: AddTemporaryUserRequestDto): ResponseEntity<HttpStatus> {
        userCombineService.addTemporaryUser(addTemporaryUserRequestDto)

        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/email-verify")
    fun verifyEmail(@RequestBody @Valid verifyEmailRequestDto: VerifyEmailRequestDto): ResponseEntity<TokenResponseDto> {
        return ResponseEntity.ok(userCombineService.verifyCode(verifyEmailRequestDto))
    }
}