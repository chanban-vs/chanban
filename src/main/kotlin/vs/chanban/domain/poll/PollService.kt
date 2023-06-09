package vs.chanban.domain.poll

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vs.chanban.domain.user.User

@Service
class PollService(private val pollRepository: PollRepository) {
    @Transactional(readOnly = true)
    fun getIsPolled(user: User): Boolean {
        return pollRepository.existsBycreatedBy(user)
    }
}