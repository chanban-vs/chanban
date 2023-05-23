package vs.chanban.domain.poll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PollRepository: JpaRepository<Poll, Long> {
}