package vs.chanban.domain.poll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vs.chanban.domain.user.User

@Repository
interface PollRepository: JpaRepository<Poll, Long> {
    fun existsBycreatedBy(user: User): Boolean
}