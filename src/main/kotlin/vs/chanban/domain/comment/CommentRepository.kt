package vs.chanban.domain.comment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {
}