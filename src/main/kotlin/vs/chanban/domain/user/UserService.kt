package vs.chanban.domain.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.common.Message.User.USER_NOT_FOUND
import vs.chanban.common.constant.Constant.User.EMAIL
import vs.chanban.common.constant.Constant.User.ID
import vs.chanban.common.exception.ChanbanBizException

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
    ) {
    fun addUser(user: User): User {
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            ChanbanBizException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND.format(ID, userId))
        }
    }

    @Transactional(readOnly = true)
    fun getUserByUserEmail(email: String): User {
        return userRepository.findByUserEmail(email).orElseThrow {
            ChanbanBizException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND.format(EMAIL, email))
        }
    }

    @Transactional(readOnly = true)
    fun existsByUserEmail(email: String): Boolean {
        return userRepository.existsByUserEmail(email)
    }
}