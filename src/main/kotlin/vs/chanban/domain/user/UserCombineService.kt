package vs.chanban.domain.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.User.DUPLICATED_EMAIL
import vs.chanban.common.Message.User.INVALID_PASSWORD_BY_LENGTH
import vs.chanban.common.Message.User.WRONG_PASSWORD
import vs.chanban.common.Message.User.WRONG_VERIFICATION_CODE
import vs.chanban.common.constant.Constant.TemporaryUser.MINIMUM_PASSWORD_LENGTH
import vs.chanban.common.constant.Constant.TemporaryUser.VERIFICATION_CODE_LENGTH
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.mail.MailService
import vs.chanban.domain.support.util.random.RandomUtil.generateVerificationCode
import vs.chanban.domain.token.TokenService
import vs.chanban.domain.token.dto.TokenResponseDto
import vs.chanban.domain.user.dto.AddTemporaryUserRequestDto
import vs.chanban.domain.user.dto.LoginRequestDto
import vs.chanban.domain.user.email.verification.TemporaryUserService
import vs.chanban.domain.user.email.verification.dto.CheckEmailExistenceRequestDto
import vs.chanban.domain.user.email.verification.dto.CheckEmailExistenceResponseDto
import vs.chanban.domain.user.email.verification.dto.VerifyEmailRequestDto

@Service
class UserCombineService(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val mailService: MailService,
    private val temporaryUserService: TemporaryUserService
) {
    fun login(loginRequestDto: LoginRequestDto): TokenResponseDto {
        val user = userService.getUserByUserEmail(loginRequestDto.email!!)

        if (passwordEncoder.matches(loginRequestDto.password, user.userPassword)) {
            val token = tokenService.generateToken(user)
            return TokenResponseDto.of(token)
        } else {
            throw ChanbanBizException(HttpStatus.UNAUTHORIZED, WRONG_PASSWORD)
        }
    }

    // Add user & send email verification code
    @Transactional
    fun addTemporaryUser(addTemporaryUserRequestDto: AddTemporaryUserRequestDto) {
        if (addTemporaryUserRequestDto.password!!.length < MINIMUM_PASSWORD_LENGTH) {
            throw ChanbanBizException(HttpStatus.BAD_REQUEST, INVALID_PASSWORD_BY_LENGTH.format(MINIMUM_PASSWORD_LENGTH))
        }
        if (existsByEmail(addTemporaryUserRequestDto.email!!)) {
            throw ChanbanBizException(HttpStatus.BAD_REQUEST, DUPLICATED_EMAIL.format(addTemporaryUserRequestDto.email))
        }

        addTemporaryUserRequestDto.setDefaultRoles()
        addTemporaryUserRequestDto.password = passwordEncoder.encode(addTemporaryUserRequestDto.password)
        addTemporaryUserRequestDto.verificationCode = generateVerificationCode(VERIFICATION_CODE_LENGTH)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch(Dispatchers.IO) {
            mailService.sendVerifyMail(addTemporaryUserRequestDto.email, addTemporaryUserRequestDto.verificationCode)
        }

        temporaryUserService.addTemporaryUser(addTemporaryUserRequestDto)
    }

    @Transactional
    fun verifyCode(verifyEmailRequestDto: VerifyEmailRequestDto): TokenResponseDto {
        val temporaryUser = temporaryUserService.getTemporaryUser(verifyEmailRequestDto.email!!)

        if (temporaryUser.verificationCode != verifyEmailRequestDto.verificationCode) {
            throw ChanbanBizException(HttpStatus.BAD_REQUEST, WRONG_VERIFICATION_CODE)
        } else {
            val user = userService.addUser(User.of(temporaryUser))

            temporaryUserService.removeTemporaryUser(verifyEmailRequestDto.email)

            val token = tokenService.generateToken(user)
            return TokenResponseDto.of(token)
        }
    }

    fun checkEmailExistence(checkEmailExistenceRequestDto: CheckEmailExistenceRequestDto): CheckEmailExistenceResponseDto {
        return CheckEmailExistenceResponseDto.of(existsByEmail(checkEmailExistenceRequestDto.email!!))
    }

    // Return false when email doesn't exist both db and redis
    private fun existsByEmail(email: String):Boolean {
        val exists = userService.existsByUserEmail(email)
        val existsInRedis = temporaryUserService.existsById(email)

        return exists || existsInRedis
    }
}