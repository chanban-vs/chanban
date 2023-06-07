package vs.chanban.domain.user.email.verification

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.User.TEMPORARY_USER_NOT_FOUND
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.domain.user.dto.AddTemporaryUserRequestDto

@Service
@Transactional
class TemporaryUserService(private val temporaryUserRepository: TemporaryUserRepository) {
    fun addTemporaryUser(addTemporaryUserRequestDto: AddTemporaryUserRequestDto) {
        val temporaryUser = TemporaryUser.of(addTemporaryUserRequestDto)
        temporaryUserRepository.save(temporaryUser)
    }

    @Transactional(readOnly = true)
    fun existsById(email: String): Boolean {
        return temporaryUserRepository.existsById(email)
    }
    @Transactional(readOnly = true)
    fun getTemporaryUser(email: String): TemporaryUser {
        val temporaryUser = temporaryUserRepository.findById(email)
        return temporaryUser.orElseThrow{
            ChanbanBizException(HttpStatus.NOT_FOUND, TEMPORARY_USER_NOT_FOUND.format(email))
        }
    }

    fun removeTemporaryUser(email: String) {
        temporaryUserRepository.deleteById(email)
    }
}